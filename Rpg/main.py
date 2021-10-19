from Game import Game
import pygame


def main():
    board_size = (800, 800)
    pygame.init()
    screen = pygame.display.set_mode(board_size)
    pygame.display.set_caption("Simple RPG")
    running = True
    game = Game(board_size)
    print(game.get_player())
    game.play(screen)
    # while running:
    #     screen.blit(pygame.image.load("images/grass.png"), (0, 0))
    #     for event in pygame.event.get():
    #         if event.type == pygame.QUIT:
    #             running = False
    # move_ticker = 0
    # keys = pygame.key.get_pressed()
    # if event.type == pygame.K_RIGHT:
    #     pass
    # pygame.display.update()

    # game = Game()
    # print(game.get_player())
    # print(game.game_board.enemies)
    # for enemy in game.game_board.enemies:
    #     game.get_player().attack(game.game_board.enemies[enemy])
    #     print(game.game_board.enemies[enemy].attributes.hp)
    # game.get_player().is_dead = True
    game.end_game()


if __name__ == '__main__':
    main()
