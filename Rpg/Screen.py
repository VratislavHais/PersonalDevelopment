from typing import Tuple
import pygame


class Screen:
    __instance = None
    __size = (800, 800)
    __usable_size = (740, 740)
    __size_with_menu = (__size[0], __size[1] + 150)

    @staticmethod
    def get_screen():
        if Screen.__instance is None:
            Screen()
        return Screen.__instance._get_screen()

    @staticmethod
    def get_size():
        return Screen.__usable_size

    @staticmethod
    def get_menu_size():
        return Screen.__size_with_menu

    @staticmethod
    def get_screen_size():
        return Screen.__size

    def __init__(self):
        if Screen.__instance is not None:
            raise RuntimeError("Screen is a singleton!")
        else:
            pygame.init()
            Screen.__instance = self
            self.__screen = pygame.display.set_mode(self.__size_with_menu)
            pygame.display.set_caption("Simple RPG")

    def _get_screen(self):
        return self.__screen
