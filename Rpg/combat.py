from Character import Character
from Enemy import Enemy


def start_combat(player: Character, enemy: Enemy):
    player.to_combat()
    while not player.is_dead.value and not enemy.is_dead.value:
        player.attack(enemy)
        enemy.attack(player)
    player.from_combat()
        