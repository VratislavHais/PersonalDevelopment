from Character import Character
from Enemy import Enemy


def combat_step(self, player: Character, enemy: Enemy):
    player.attack_or_spell(enemy)
    enemy.attack_or_spell(player)
    if player.is_dead.value:
        player.end_game()
    elif enemy.is_dead.value:
        self.enemies.remove(enemy)
        player.from_combat()

