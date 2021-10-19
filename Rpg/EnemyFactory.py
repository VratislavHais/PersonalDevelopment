from abc import ABC, abstractmethod
import Enemy
from random import randint


class EnemyFactory(ABC):
    @property
    @abstractmethod
    def available_enemies(self):
        pass

    @property
    @abstractmethod
    def boss(self):
        pass
    
    def generate_random_enemy(self) -> Enemy.Enemy:
        return self.get_enemy()()

    def get_enemy(self):
        return self.available_enemies[randint(0, len(self.available_enemies)-1)]


class GoblinFactory(EnemyFactory):
    available_enemies = (Enemy.GoblinWarrior, Enemy.GoblinMage)
    boss = Enemy.GoblinBoss()


class OgreFactory(EnemyFactory):
    available_enemies = (Enemy.OgreWarrior, Enemy.OgreMage)
    boss = Enemy.OgreBoss()
