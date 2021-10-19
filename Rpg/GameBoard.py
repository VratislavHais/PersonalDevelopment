from Coordinates import Coordinates
from random import randint
from Enemy import Enemy
import EnemyFactory


class GameBoard:
    def __init__(self, number_of_enemies: int, enemy_type: str, round_number: int):
        self.enemies = self._generate_enemies(number_of_enemies, enemy_type, round_number)
        self.board_size = 6

    def _generate_enemies(self, number_of_enemies: int, enemy_type: str, round_number: int) -> dict:
        result = {}
        factory = self._enemy_factory(enemy_type)
        for i in range(number_of_enemies):
            coord = self._random_coord()
            while coord in result:
                coord = self._random_coord()
            result[self._random_coord()] = self._random_enemy(factory)
        if round_number % 4 == 0:
            result[self._random_coord()] = self._boss(factory)
        return result

    def _random_coord(self):
        return Coordinates(x=randint(0, 6), y=randint(0, 6))

    def _random_enemy(self, factory: EnemyFactory) -> Enemy:
        return factory.generate_random_enemy()

    def _boss(self, factory: EnemyFactory) -> Enemy:
        return factory.boss

    def _enemy_factory(self, enemy_type: str) -> EnemyFactory:
        if enemy_type == "goblins":
            return EnemyFactory.GoblinFactory()
        elif enemy_type == "ogre":
            return EnemyFactory.OgreFactory()
