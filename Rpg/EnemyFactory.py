from abc import ABC, abstractmethod
import Enemy
from random import randint


class EnemyFactory(ABC):
    @property
    @abstractmethod
    def available_enemies(self):
        pass

    @abstractmethod
    def boss(self, coordinates):
        pass

    def generate_random_enemy(self, coordinates) -> Enemy.Enemy:
        return self.get_enemy()(coordinates)

    def get_enemy(self):
        return self.available_enemies[randint(0, len(self.available_enemies) - 1)]


class GoblinFactory(EnemyFactory):
    available_enemies = (Enemy.GoblinWarrior, Enemy.GoblinMage)

    def boss(self, coordinates):
        return Enemy.GoblinBoss(coordinates)


class OgreFactory(EnemyFactory):
    available_enemies = (Enemy.OgreWarrior, Enemy.OgreMage)

    def boss(self, coordinates):
        return Enemy.OgreBoss(coordinates)
