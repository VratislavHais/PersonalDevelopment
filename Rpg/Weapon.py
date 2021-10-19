from abc import ABC, abstractmethod


class Weapon(ABC):
    @property
    @abstractmethod
    def damage(self):
        pass


class Fist(Weapon):
    damage = 2


class Staff(Weapon):
    damage = 8


class Dagger(Weapon):
    damage = 11


class RustySword(Weapon):
    damage = 12


class Scythe(Weapon):
    damage = 15


class Mace(Weapon):
    damage = 17


class Frostmourne(Weapon):
    damage = 30
