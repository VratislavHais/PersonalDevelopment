from GameBoard import GameBoard
from ClassesFactory import ClassesFactory
from Player import Player
from Coordinates import Coordinates
from Screen import Screen


class Game:
    X = 0
    Y = 1
    ENEMY_TYPES = ("goblin", "ogre")

    def __init__(self):
        self._round_number = 0
        self.player = self._create_new_player()

    def play(self):
        quit_ = False
        while not self.player.is_dead.value and not quit_:
            game_board = self._new_game_board(self.ENEMY_TYPES[self._round_number % len(self.ENEMY_TYPES)])
            quit_ = game_board.play(self.player)

    def end_game(self):
        self.player.end_game()

    def get_player(self):
        return self.player

    def _new_game_board(self, enemy_type: str) -> GameBoard:
        self._round_number += 1
        return GameBoard(4 + self._round_number // 2, enemy_type, self._round_number)

    def _create_new_player(self) -> Player:
        factory = ClassesFactory()
        print("Available Classes:")
        print(factory)
        picked_class = input("Choose class: ")
        while not picked_class.isdigit() or (int(picked_class)-1 >= len(factory) or int(picked_class)-1 < 0):
            print("Invalid input!")
            picked_class = input("Choose class: ")

        picked_name = input("Choose name: ")
        return factory.pick_class(int(picked_class) - 1, picked_name, Coordinates(0, Screen.get_size()[self.Y]))
