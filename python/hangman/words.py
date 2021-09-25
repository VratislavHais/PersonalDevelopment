import requests
from random import choice


def get_random_word():
    words_url = "https://www.randomlists.com/data/words.json"
    words_json = requests.get(url=words_url).json()["data"]
    return choice(words_json)
