import pygame
from Screen import Screen
from Quit import Quit


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
            width, y = Screen.get_screen_size()
            height = Screen.get_menu_size()[1] - y
            self.rectangle = pygame.Rect(0, y + 3, width, height)
            self.black_line = pygame.Rect(0, y, width, 3)
            self.color = (140, 100, 31)
            self.x_text = 60
            self.y_text = y + 30
            self.y_space = 60
            self.x_space = 250
            pygame.font.init()
            self.font = pygame.font.SysFont("urwgothic", 30)
            Menu.__instance = self

    @staticmethod
    def display(items=None, selected=0):
        menu = Menu.get_instance()
        menu.display_box()
        if items is not None:
            pygame.draw.polygon(Screen.get_screen(), (0, 0, 0), (
                (menu.x_text - 30 + (selected // 2) * menu.x_space, menu.y_text + (selected % 2) * menu.y_space),
                (menu.x_text - 30 + (selected // 2) * menu.x_space, menu.y_text + 20 + (selected % 2) * menu.y_space),
                (menu.x_text - 10 + (selected // 2) * menu.x_space, menu.y_text + 10 + (selected % 2) * menu.y_space)))
            for i, item in enumerate(items):
                Screen.get_screen().blit(menu.font.render(item, True, (0, 0, 0)),
                                         (menu.x_text + (i // 2) * menu.x_space, menu.y_text + (i % 2) * menu.y_space))

    @staticmethod
    def display_message_internal(message: str):
        menu = Menu.get_instance()
        menu.display_box()
        Screen.get_screen().blit(menu.font.render(message, True, (0, 0, 0)),
                                 (menu.x_text, menu.y_text))
        pygame.display.update()

    def display_box(self):
        pygame.draw.rect(Screen.get_screen(), self.color, self.rectangle)
        pygame.draw.rect(Screen.get_screen(), (0, 0, 0), self.black_line)

    @staticmethod
    def change_selected_item(change: int, items, current) -> int:
        if len(items) > current + change >= 0:
            return current + change
        else:
            return current

    @staticmethod
    def move(action, items, current) -> int:
        if action in Menu.__DIRECTIONS:
            return Menu.change_selected_item(Menu.__DIRECTIONS[action], items, current)
        return current

    @staticmethod
    def get_input(items) -> int:
        selected = 0
        while not Quit.quit_:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    Quit.quit_ = True
                elif event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_RETURN:
                        return selected
                    selected = Menu.move(event.key, items, selected)
            Menu.display(items, selected)
            pygame.display.update()

    @staticmethod
    def show_message(message: str):
        while not Quit.quit_:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    Quit.quit_ = True
                elif event.type == pygame.KEYDOWN and event.key == pygame.K_RETURN:
                    return
            Menu.display_message_internal(message + " (Press Enter to Continue)")
            pygame.display.update()
