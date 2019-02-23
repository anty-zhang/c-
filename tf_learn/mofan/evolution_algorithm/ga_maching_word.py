# -*- coding: utf-8 -*-
"""
Visualize Genetic Algorithm to match the target phrase.
Visit my tutorial website for more: https://morvanzhou.github.io/tutorials/
"""
import numpy as np

TARGET_PHRASE = 'You get it!'   # target DNA
POP_SIZE = 300                  # population size
CROSS_RATE = 0.4                # mating probability(DNA crossover)
MUTATION_RATE = 0.01            # mutation probability
N_GENERATIONS = 1000

DNA_SIZE = len(TARGET_PHRASE)
TARGET_ASCII = np.fromstring(TARGET_PHRASE, dtype=np.uint8)    # convert string to number
ASCII_BOUND = [32, 126]


class GA(object):
    def __init__(self, DNA_size, DNA_bound, cross_rate, mutation_rate, pop_size):
        self.DNA_size = DNA_size
        DNA_bound[1] += 1
        self.DNA_bound = DNA_bound
        self.cross_rate = cross_rate
        self.mutation_rate = mutation_rate
        self.pop_size = pop_size
        self.pop = np.random.randint(*DNA_bound, size=(pop_size, DNA_size)).astype(np.int8) # int8 for convert to ASCII

    def translate_DNA(self, DNA):
        # convert to readable string
        return DNA.tostring().decode('ascii')

    def get_fitness(self):  # count how many character matches
        # 用这个句子长度的 DNA 来生成这个句子. 每个 DNA 代表一个字母. 如果对上的字母越多, 我的 fitness 就越高
        match_count = (self.pop == TARGET_ASCII).sum(axis=1)
        return match_count

    def select(self):
        fitness = self.get_fitness() + 1e-4     # add a small amount to avoid all zero fitness
        idx = np.random.choice(np.arange(self.pop_size), size=self.pop_size, replace=True, p=fitness/fitness.sum())
        return self.pop[idx]

    def crossover(self, parent, pop):
        if np.random.rand() < self.cross_rate:
            i_ = np.random.randint(0, self.pop_size, size=1)    # select another individual from pop
            cross_points = np.random.randint(0, 2, self.DNA_size).astype(np.bool)   # choose crossover points
            parent[cross_points] = pop[i_, cross_points]    # matching and produce one child

        return parent

    def mutate(self, child):
        for point in range(self.DNA_size):
            if np.random.rand() < self.mutation_rate:
                child[point] = np.random.randint(*self.DNA_bound)   # choose a random ASCII index

        return child

    # 进化
    def evolve(self):
        pop = self.select()
        pop_copy = pop.copy()
        for parent in pop:      # for every parent
            child = self.crossover(parent, pop_copy)
            child = self.mutate(child)
            parent[:] = child

        self.pop = pop

if __name__ == "__main__":
    ga = GA(DNA_size=DNA_SIZE, DNA_bound=ASCII_BOUND, cross_rate=CROSS_RATE, mutation_rate=MUTATION_RATE,
            pop_size=POP_SIZE)

    for generation in range(N_GENERATIONS):
        fitness = ga.get_fitness()
        best_DNA = ga.pop[np.argmax(fitness)]
        best_phrase = ga.translate_DNA(best_DNA)
        print("Gen", generation, ": ", best_phrase)

        if best_phrase == TARGET_PHRASE:
            break

        ga.evolve()

    """
    output:
    Gen 0 :  fof*`NqIe:n
    Gen 1 :  /Ju=oxN \z)
    Gen 2 :  Yo;nzq@L}t-
    Gen 3 :  Yo_ gw/m?~:
    Gen 4 :  Yoi0ge_ D>:
    Gen 5 :  Yoi0ge_ D>:
    ...
    Gen 152 :  You get it9
    Gen 153 :  You get itW
    Gen 154 :  You get it9
    Gen 155 :  You get itA
    Gen 156 :  You get it_
    Gen 157 :  You get it!
    """
