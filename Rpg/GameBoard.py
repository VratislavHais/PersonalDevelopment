from Coordinates import Coordinates
from Enemy import Enemy
from Character import Character
from typing import Tuple
from Screen import Screen
import EnemyFactory
import pygame
import combat


class GameBoard:
    MOVEMENTS = {
        pygame.K_LEFT: (-10, 0),
        pygame.K_RIGHT: (10, 0),
        pygame.K_UP: (0, -10),
        pygame.K_DOWN: (0, 10)
    }

    def __init__(self, number_of_enemies: int, enemy_type: str, round_number: int, board_size: Tuple[int, int]):
        self.board_size = board_size
        self.enemies = self._generate_enemies(number_of_enemies, enemy_type, round_number)
        print(self.enemies)

    def _generate_enemies(self, number_of_enemies: int, enemy_type: str, round_number: int) -> list:
        result = []
        factory = self._enemy_factory(enemy_type)
        for i in range(number_of_enemies):
            result.append(self._random_enemy(factory, self._random_unique_coord(result)))
        if round_number % 4 == 0:
            result.append(self._boss(factory, self._random_unique_coord(result)))
        return result

    def _random_unique_coord(self, list_of_enemies) -> Coordinates:
        coord = Coordinates(rand=True, board_size=self.board_size)
        while coord.is_in_list(list_of_enemies):
            coord = Coordinates(rand=True, board_size=self.board_size)
        return coord

    def _random_enemy(self, factory: EnemyFactory, coordinates: Coordinates) -> Enemy:
        return factory.generate_random_enemy(coordinates)

    def _boss(self, factory: EnemyFactory, coordinates: Coordinates) -> Enemy:
        return factory.boss(coordinates)

    def _enemy_factory(self, enemy_type: str) -> EnemyFactory:
        if enemy_type == "goblins":
            return EnemyFactory.GoblinFactory()
        elif enemy_type == "ogre":
            return EnemyFactory.OgreFactory()

    def play(self, player: Character) -> bool:
        quit_ = False
        while not player.is_dead.value and not quit_ and len(self.enemies) > 0:
            for event in pygame.event.get():
                Screen.get_screen().fill((78, 138, 58))
                if event.type == pygame.QUIT:
                    quit_ = True
                if event.type == pygame.KEYDOWN and event.key in self.MOVEMENTS:
                    self._change_player_coords(player, event.key)
            if player.coordinates.is_in_list(self.enemies):
                enemy = player.coordinates.retrieve_from_list(self.enemies)
                combat.start_combat(player, enemy)
                if player.is_dead.value:
                    player.end_game()
                else:
                    self.enemies.remove(enemy)
            self._show_enemies()
            player.display()
            pygame.display.update()
        return quit_

    def _change_player_coords(self, player: Character, event):
        player.move(self.MOVEMENTS[event], self.board_size)

    def _show_enemies(self):
        for enemy in self.enemies:
            enemy.display()
