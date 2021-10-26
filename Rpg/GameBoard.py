import combat
from Coordinates import Coordinates
import Enemy
from Player import Player
from Screen import Screen
from Menu import Menu
import pygame


class GameBoard:
    MOVEMENTS = {
        pygame.K_LEFT: (-0.2, 0),
        pygame.K_RIGHT: (0.2, 0),
        pygame.K_UP: (0, -0.2),
        pygame.K_DOWN: (0, 0.2)
    }

    def __init__(self, number_of_enemies: int, enemy_type: str, round_number: int):
        self.board_size = Screen.get_size()
        self.enemies = self._generate_enemies(number_of_enemies, enemy_type, round_number)

    def _generate_enemies(self, number_of_enemies: int, enemy_type: str, round_number: int) -> list:
        result = []
        factory = self._enemy_factory(enemy_type)
        for i in range(number_of_enemies):
            result.append(self._random_enemy(factory, self._random_unique_coord(result)))
        if round_number % 4 == 0:
            result.append(self._boss(factory, self._random_unique_coord(result)))
        return result

    def _random_unique_coord(self, list_of_enemies) -> Coordinates:
        updated_size = (self.board_size[0] - 60, self.board_size[1] - 60)
        coord = Coordinates(rand=True, board_size=updated_size)
        while coord.is_in_list(list_of_enemies) or coord.y < 20:
            coord = Coordinates(rand=True, board_size=updated_size)
        return coord

    def _random_enemy(self, factory: Enemy.EnemyFactory, coordinates: Coordinates) -> Enemy:
        return factory.generate_random_enemy(coordinates)

    def _boss(self, factory: Enemy.EnemyFactory, coordinates: Coordinates) -> Enemy:
        return factory.boss(coordinates)

    def _enemy_factory(self, enemy_type: str) -> Enemy.EnemyFactory:
        if enemy_type == "goblin":
            return Enemy.GoblinFactory()
        elif enemy_type == "ogre":
            return Enemy.OgreFactory()

    def play(self, player: Player) -> bool:
        quit_ = False
        enemy = None
        # menu = Menu()
        # menu.set_items(("item1", "item2", "item3", "item4"))
        while not player.is_dead.value and not quit_ and len(self.enemies) > 0:
            if len(self.enemies) < 1:
                break
            if player.in_combat.value:
                combat.combat_step(player, enemy)
                if enemy.is_dead.value:
                    self.enemies.remove(enemy)
                    player.add_xp(enemy.xp_on_kill)
            else:
                Screen.get_screen().fill((78, 138, 58))
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        quit_ = True
                self._change_player_coords(player, pygame.key.get_pressed())
                if player.coordinates.is_in_list(self.enemies):
                    enemy = player.coordinates.retrieve_from_list(self.enemies)
                    player.to_combat()
            self._show_enemies()
            player.display()
            # menu.display()
            pygame.display.update()
        return quit_

    def _change_player_coords(self, player: Player, key_pressed_list):
        x = y = 0
        for key in self.MOVEMENTS:
            if key_pressed_list[key]:
                x += self.MOVEMENTS[key][0]
                y += self.MOVEMENTS[key][1]
        player.move(x, y)

    def _show_enemies(self):
        for enemy in self.enemies:
            enemy.display()
