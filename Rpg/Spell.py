from abc import ABC, abstractmethod


class Spell(ABC):
    @property
    @abstractmethod
    def mana_consumption(self):
        pass

    @property
    @abstractmethod
    def damage(self):
        pass

    def __str__(self):
        return self.__class__.__name__ + " (mana cost: " + str(self.mana_consumption) + ", damage: " + str(self.damage)\
            + ")"


class Fireball(Spell):
    mana_consumption = 15
    damage = 30


class Heal(Spell):
    mana_consumption = 5
    damage = -30  # heal


class MagicMissile(Spell):
    mana_consumption = 5
    damage = 15
