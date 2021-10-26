from abc import ABC, abstractmethod
from random import randint
from Character import Character
import pygame
import Weapon
import Spell


class Enemy(Character, ABC):
    def __init__(self, max_hp: float, max_mp: float, str_: int, int_: int, agi: int, weapon: Weapon,
                 coordinates, xp_on_kill: int, drop: Weapon, spells=None):
        super().__init__(max_hp, max_mp, str_, int_, agi, weapon, coordinates, spells)
        self.xp_on_kill = xp_on_kill
        self.drop_on_death = drop

    def attack_or_spell(self, target):
        if self.spells is not None and self.attributes.mp.value >= 10:
            self.cast_spell(0, target)
        else:
            self.weapon_attack(target)

    def pick_spell(self):
        pass


class GoblinWarrior(Enemy):
    image = pygame.image.load("images/goblin.png")

    def __init__(self, coordinates):
        super().__init__(100, 0, 5, 1, 5, Weapon.RustySword, coordinates, 50, Weapon.RustySword)
        self.name = self.__class__.__name__


class GoblinMage(Enemy):
    image = pygame.image.load("images/goblin.png")

    def __init__(self, coordinates):
        super().__init__(70, 20, 1, 5, 4, Weapon.Staff, coordinates, 50, Weapon.Staff, [Spell.Fireball])
        self.name = self.__class__.__name__


class GoblinBoss(Enemy):
    image = pygame.image.load("images/goblin_boss.png")

    def __init__(self, coordinates):
        super().__init__(120, 25, 8, 6, 8, Weapon.Scythe, coordinates, 150, Weapon.Scythe, [Spell.Fireball])
        self.name = "Scumrat"


class OgreWarrior(Enemy):
    image = pygame.image.load("images/ogre_warrior.png")

    def __init__(self, coordinates):
        super().__init__(120, 0, 6, 1, 2, Weapon.Mace, coordinates, 100, Weapon.Mace)
        self.name = self.__class__.__name__


class OgreMage(Enemy):
    image = pygame.image.load("images/ogre_mage.png")

    def __init__(self, coordinates):
        super().__init__(100, 10, 1, 4, 1, Weapon.Mace, coordinates, 100, Weapon.Mace, [Spell.MagicMissile])
        self.name = self.__class__.__name__


class OgreBoss(Enemy):
    image = pygame.image.load("images/ogre_boss.png")

    def __init__(self, coordinates):
        super().__init__(250, 20, 12, 3, 4, Weapon.Mace, coordinates, 300, Weapon.Frostmourne, [Spell.Fireball])
        self.name = "Dummydumdum"


class EnemyFactory(ABC):
    @property
    @abstractmethod
    def available_enemies(self):
        pass

    @abstractmethod
    def boss(self, coordinates):
        pass

    def generate_random_enemy(self, coordinates) -> Enemy:
        return self.get_enemy()(coordinates)

    def get_enemy(self):
        return self.available_enemies[randint(0, len(self.available_enemies) - 1)]


class GoblinFactory(EnemyFactory):
    available_enemies = (GoblinWarrior, GoblinMage)

    def boss(self, coordinates):
        return GoblinBoss(coordinates)


class OgreFactory(EnemyFactory):
    available_enemies = (OgreWarrior, OgreMage)

    def boss(self, coordinates):
        return OgreBoss(coordinates)
