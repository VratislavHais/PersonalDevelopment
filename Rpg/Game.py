from GameBoard import GameBoard
from ClassesFactory import ClassesFactory
from Character import Character
from Coordinates import Coordinates
from typing import Tuple
from Screen import Screen
import pygame


class Game:
    X = 0
    Y = 1
    MOVEMENTS = {
        pygame.K_LEFT: (-10, 0),
        pygame.K_RIGHT: (10, 0),
        pygame.K_UP: (0, -10),
        pygame.K_DOWN: (0, 10)
    }

    def __init__(self, board_size: Tuple[int, int]):
        self._round_number = 0
        self.board_size = (board_size[self.X] - 60, board_size[self.Y] - 60)
        self.game_board = self._new_game_board()
        self.player = self._create_new_player()
        Screen(board_size)

    def play(self):
        quit_ = False
        while not self.player.is_dead.value and not quit_:
            quit_ = self.game_board.play(self.player)
            # for event in pygame.event.get():
            #     screen.fill((78, 138, 58))
            #     if event.type == pygame.QUIT:
            #         self.end_game()
            #         quit_ = True
            #     if event.type == pygame.KEYDOWN and event.key in self.MOVEMENTS:
            #         self._change_player_coords(event.key)
            # screen.blit(self.player.image, (self.player.coordinates.values()))
            # pygame.display.update()

    def end_game(self):
        self.player.end_game()

    def get_player(self):
        return self.player

    def _new_game_board(self) -> GameBoard:
        self._round_number += 1
        return GameBoard(4, "goblins", self._round_number, self.board_size)

    def _create_new_player(self) -> Character:
        factory = ClassesFactory()
        print("Available Classes:")
        print(factory)
        picked_class = input("Choose class: ")
        while not isinstance(picked_class, int) and int(picked_class)-1 >= len(factory):
            print("Invalid input!")
            picked_class = input("Choose class: ")

        picked_name = input("Choose name: ")
        return factory.pick_class(int(picked_class) - 1, picked_name, Coordinates(0, self.board_size[self.Y]))

    def _change_player_coords(self, event):
        self.player.coordinates.update(self.MOVEMENTS[event], self.board_size)
