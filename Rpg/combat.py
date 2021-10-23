from Character import Character
from Enemy import Enemy


def combat_step(player: Character, enemy: Enemy):
    player.attack_or_spell(enemy)
    if not enemy.is_dead.value:
        enemy.attack_or_spell(player)
    if player.is_dead.value:
        player.end_game()
    elif enemy.is_dead.value:
        player.from_combat()
