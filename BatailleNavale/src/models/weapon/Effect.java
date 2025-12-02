package models.weapon;

public class Effect {
    private int _x;
    private int _y;
    private EffectType _effect;

    public Effect(int x, int y, EffectType effect) {
        this._x = x;
        this._y = y;
        this._effect = effect;
    }

    public int[] getPos() {
        return new int[]{this._x, this._y};
    }

    public EffectType getEffectType() {
        return this._effect;
    }
}
