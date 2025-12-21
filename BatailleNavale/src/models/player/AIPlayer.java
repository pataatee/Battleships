package models.player;

import models.game.logs.GameLogs;
import models.game.logs.Log;
import models.grid.Grid;
import models.weapon.Missile;

import java.util.ArrayList;
import java.util.Random;

public class AIPlayer extends Player {
    private int _gSize;
    private Random _random;

    private enum HuntingMode {
        RANDOM,
        TARGET,
        DESTROY
    }

    private HuntingMode _mode;
    private int[] _lastHitCell;
    private int[] _firstHitCell;
    private ArrayList<int[]> _pendingTargets;
    private int[] _currentDirection;

    public AIPlayer(String name, int id, Grid grid) {
        super(name, id, grid, PlayerType.AI);
        _gSize = grid.getSize();
        _random = new Random();
        _mode = HuntingMode.RANDOM;
        _pendingTargets = new ArrayList<>();
        super.setWeapon(new Missile());
    }

    public int[] generateAttackCoordinates() {
        switch (_mode) {
            case TARGET:
                return getNextTargetCoordinate();
            case DESTROY:
                return getNextDestroyCoordinate();
            case RANDOM:
            default:
                return getRandomCoordinate();
        }
    }

    private int[] getRandomCoordinate() {
        return new int[]{_random.nextInt(_gSize), _random.nextInt(_gSize)};
    }

    private int[] getNextTargetCoordinate() {
        if (_pendingTargets.isEmpty()) {
            _mode = HuntingMode.RANDOM;
            return getRandomCoordinate();
        }

        return _pendingTargets.remove(0);
    }

    private int[] getNextDestroyCoordinate() {
        if (_currentDirection == null || _lastHitCell == null) {
            _mode = HuntingMode.RANDOM;
            return getRandomCoordinate();
        }

        int nextX = _lastHitCell[0] + _currentDirection[0];
        int nextY = _lastHitCell[1] + _currentDirection[1];

        if (isValidCoordinate(nextX, nextY)) {
            return new int[]{nextX, nextY};
        } else {
            reverseDirection();
            return getNextTargetCoordinate();
        }
    }

    public void processAttackResult(int x, int y, ShotResultType result) {
        switch (result) {
            case HIT:
                onHit(x, y);
                break;
            case SUNK:
                onSunk(x, y);
                break;
            case MISS:
                onMiss(x, y);
                break;
            default:
                break;
        }
    }

    private void onHit(int x, int y) {
        _lastHitCell = new int[]{x, y};

        if (_mode == HuntingMode.RANDOM) {
            _mode = HuntingMode.TARGET;
            _firstHitCell = new int[]{x, y};
            addAdjacentTargets(x, y);
        } else if (_mode == HuntingMode.TARGET) {
            _mode = HuntingMode.DESTROY;
            determineDirection();
            _pendingTargets.clear();
        }
    }

    private void onSunk(int x, int y) {
        _mode = HuntingMode.RANDOM;
        _lastHitCell = null;
        _firstHitCell = null;
        _currentDirection = null;
        _pendingTargets.clear();
    }

    private void onMiss(int x, int y) {
        if (_mode == HuntingMode.DESTROY) {
            reverseDirection();
            if (_pendingTargets.isEmpty() && _firstHitCell != null) {
                _lastHitCell = _firstHitCell;
            }
        }
    }

    private void addAdjacentTargets(int x, int y) {
        int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValidCoordinate(newX, newY)) {
                _pendingTargets.add(new int[]{newX, newY});
            }
        }

        if (_pendingTargets.size() > 1) {
            for (int i = _pendingTargets.size() - 1; i > 0; i--) {
                int j = _random.nextInt(i + 1);
                int[] temp = _pendingTargets.get(i);
                _pendingTargets.set(i, _pendingTargets.get(j));
                _pendingTargets.set(j, temp);
            }
        }
    }

    private void determineDirection() {
        if (_firstHitCell == null || _lastHitCell == null) {
            return;
        }

        int dx = _lastHitCell[0] - _firstHitCell[0];
        int dy = _lastHitCell[1] - _firstHitCell[1];

        // Normaliser la direction (soit -1, 0 ou 1)
        if (dx != 0) dx = dx / Math.abs(dx);
        if (dy != 0) dy = dy / Math.abs(dy);

        _currentDirection = new int[]{dx, dy};
    }

    private void reverseDirection() {
        if (_currentDirection != null) {
            _currentDirection[0] = -_currentDirection[0];
            _currentDirection[1] = -_currentDirection[1];

            if (_firstHitCell != null) {
                _lastHitCell = _firstHitCell;
            }
        } else {
            _mode = HuntingMode.TARGET;
            if (_firstHitCell != null) {
                addAdjacentTargets(_firstHitCell[0], _firstHitCell[1]);
            }
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < _gSize && y >= 0 && y < _gSize;
    }

    public Attack generateAttack() {
        int[] coords = generateAttackCoordinates();
        return createAttack(coords[0], coords[1]);
    }


    @Override
    public void notifyDeath(GameLogs logs) {
        logs.addLog(new Log("Player " + super.getName() + ", ID: " + super.getId() + " has been defeated !"));
    }
}