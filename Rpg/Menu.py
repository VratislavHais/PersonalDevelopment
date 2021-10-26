import pygame
from Screen import Screen


class Menu:
    __instance = None
    __DIRECTIONS = {
        pygame.K_LEFT: -2,
        pygame.K_RIGHT: 2,
        pygame.K_DOWN: 1,
        pygame.K_UP: -1
    }

    @staticmethod
    def get_instance():
        if Menu.__instance is None:
            Menu()
        return Menu.__instance
    
    def __init__(self):
        if Menu.__instance is not None:
            raise RuntimeError("Menu is a singleton!")
        else:
            self.menu_items = []
            self.selected_item = 0
            width, y = Screen.get_screen_size()
            height = Screen.get_menu_size()[1] - y
            self.rectangle = pygame.Rect(0, y + 3, width, height)
            self.black_line = pygame.Rect(0, y, width, 3)
            self.color = (140, 100, 31)
            self.x_text = 60
            self.y_text = y + 30
            pygame.font.init()
            self.font = pygame.font.SysFont("urwgothic", 30)
            Menu.__instance = self

    @staticmethod
    def display():
        menu = Menu.get_instance()
        pygame.draw.rect(Screen.get_screen(), menu.color, menu.rectangle)
        pygame.draw.rect(Screen.get_screen(), (0, 0, 0), menu.black_line)
        pygame.draw.polygon(Screen.get_screen(), (0, 0, 0), (
            (menu.x_text - 30 + (menu.selected_item // 2) * 150, menu.y_text + (menu.selected_item % 2) * 60),
            (menu.x_text - 30 + (menu.selected_item // 2) * 150, menu.y_text + 20 + (menu.selected_item % 2) * 60),
            (menu.x_text - 10 + (menu.selected_item // 2) * 150, menu.y_text + 10 + (menu.selected_item % 2) * 60)))
        for i, item in enumerate(menu.menu_items):
            Screen.get_screen().blit(menu.font.render(item, True, (0, 0, 0)),
                                     (menu.x_text + (i // 2) * 150, menu.y_text + (i % 2) * 60))

    @staticmethod
    def set_items(items):
        Menu.get_instance().menu_items = items

    def change_selected_item(self, change: int):
        if len(self.menu_items) > self.selected_item + change >= 0:
            self.selected_item += change

    @staticmethod
    def move(action):
        menu = Menu.get_instance()
        if action in Menu.__DIRECTIONS:
            menu.change_selected_item(Menu.__DIRECTIONS[action])

    @staticmethod
    def get_selected():
        return Menu.get_instance().menu_items[Menu.get_instance().selected_item]

    @staticmethod
    def get_selected_int():
        return Menu.get_instance().selected_item
