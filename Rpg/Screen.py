from typing import Tuple
import pygame


class Screen:
    __instance = None
    __size = (800, 800)

    @staticmethod
    def get_screen():
        if Screen.__instance is None:
            Screen(Screen.__size)
        return Screen.__instance._get_screen()

    @staticmethod
    def get_size():
        return Screen.__size

    def __init__(self, size: Tuple[int, int]):
        if Screen.__instance is not None:
            raise RuntimeError("Screen is a singleton!")
        else:
            pygame.init()
            Screen.__instance = self
            self.__screen = pygame.display.set_mode(size)
            pygame.display.set_caption("Simple RPG")

    def _get_screen(self):
        return self.__screen
