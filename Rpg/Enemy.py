from abc import ABC
from Character import Character
import pygame
import Weapon


class Enemy(Character, ABC):
    def __init__(self, max_hp: float, max_mp: float, str_: int, int_: int, agi: int, weapon: Weapon,
                 xp_on_kill: int, drop: Weapon):
        super().__init__(max_hp, max_mp, 0, 0, str_, int_, agi, weapon)
        self.xp_on_kill = xp_on_kill
        self.drop_on_death = drop

    def run_regen(self):
        pass


class GoblinWarrior(Enemy):
    image = pygame.image.load("images/goblin.png")

    def __init__(self):
        super().__init__(100, 0, 5, 1, 5, Weapon.RustySword, 50, Weapon.RustySword)
        self.name = self.__class__.__name__


class GoblinMage(Enemy):
    image = pygame.image.load("images/goblin.png")

    def __init__(self):
        super().__init__(70, 20, 1, 5, 4, Weapon.Staff, 50, Weapon.Staff)
        self.name = self.__class__.__name__


class GoblinBoss(Enemy):
    image = pygame.image.load("images/goblin_boss.png")

    def __init__(self):
        super().__init__(120, 25, 8, 6, 8, Weapon.Scythe, 150, Weapon.Scythe)
        self.name = "Scumrat"


class OgreWarrior(Enemy):
    image = pygame.image.load("images/ogre_warrior.png")

    def __init__(self):
        super().__init__(120, 0, 6, 1, 2, Weapon.Mace, 100, Weapon.Mace)
        self.name = self.__class__.__name__


class OgreMage(Enemy):
    image = pygame.image.load("images/ogre_mage.png")

    def __init__(self):
        super().__init__(100, 10, 1, 4, 1, Weapon.Mace, 100, Weapon.Mace)
        self.name = self.__class__.__name__


class OgreBoss(Enemy):
    image = pygame.image.load("images/ogre_boss.png")

    def __init__(self):
        super().__init__(250, 20, 12, 3, 4, Weapon.Mace, 300, Weapon.Frostmourne)
        self.name = "Dummydumdum"
