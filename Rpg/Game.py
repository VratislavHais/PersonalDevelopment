from GameBoard import GameBoard
from Player import Player, ClassesFactory
from Coordinates import Coordinates
from Screen import Screen
from Menu import Menu
from Quit import Quit
import pygame


class Game:
    X = 0
    Y = 1
    ENEMY_TYPES = ("goblin", "ogre")

    def __init__(self):
        self._round_number = 0
        self.player = self._create_new_player()

    def play(self):
        while not self.player.is_dead.value and not Quit.quit_:
            game_board = self._new_game_board(self.ENEMY_TYPES[self._round_number % len(self.ENEMY_TYPES)])
            game_board.play(self.player)

    def end_game(self):
        self.player.end_game()

    def get_player(self):
        return self.player

    def _new_game_board(self, enemy_type: str) -> GameBoard:
        self._round_number += 1
        return GameBoard(4 + self._round_number // 2, enemy_type, self._round_number)

    def _create_new_player(self) -> Player:
        factory = ClassesFactory()
        pygame.font.init()
        Screen.get_screen().fill((0, 0, 0))
        Screen.get_screen().blit(pygame.font.SysFont("urwgothic", 60).render("Choose Your Class!",
                                                                             True, (255, 0, 0)),
                                 (100, Screen.get_screen_size()[1] // 2))
        return factory.pick_class(Menu.get_input(factory.available_classes_as_string()),
                                  Coordinates(0, Screen.get_size()[self.Y]))
