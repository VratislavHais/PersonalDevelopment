from Character import Character
from Enemy import Enemy


def combat(player: Character, enemy: Enemy):
    # player.to_combat()
    # while not player.is_dead.value and not enemy.is_dead.value:
    player.attack_or_spell(enemy)
    enemy.attack_or_spell(player)
    # player.from_combat()
