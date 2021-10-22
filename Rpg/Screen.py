from typing import Tuple
import pygame


class Screen:
    __instance = None

    @staticmethod
    def get_screen(size=None):
        if Screen.__instance is None:
            Screen(size)
        return Screen.__instance._get_screen()

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
