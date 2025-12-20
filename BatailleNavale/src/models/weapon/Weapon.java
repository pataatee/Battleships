package models.weapon;

public abstract class Weapon {
    WeaponType _type;

    public Weapon(WeaponType type) {
        _type = type;
    }

    public abstract Effect[] use(int x, int y);

    public WeaponType get_type() {
        return _type;
    }

    public String toString() {
        return this._type.toString();
    }
}
