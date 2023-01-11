#Download store_data.csv file
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from apyori import apriori
store_data = pd.read_csv("F:\ADS\store_data.csv", header=None)
#display(store_data.head())
#print(store_data.shape)
records = []
for i in range(1,10):
    records.append([str(store_data.values[i, j]) for j in range(0, 20)])
    print(type(records))
min_sup=float(input("Enter minimum support(in float format):"))
min_conf=float(input("Enter minimum confidence(in float format):"))
#min_support=0.0045, min_confidence=0.2
association_rules = apriori(records, min_support=min_sup, min_confidence=min_conf, min_lift=3, min_length=2)
association_results = list(association_rules)
print("There are {} Relation derived.".format(len(association_results)))
for i in range(0, len(association_results)):
    print(association_results[i][0])

for item in association_results:
    # first index of the inner list
    # Contains base item and add item
    pair = item[0]
    items = [x for x in pair]
    print("Rule: " + items[0] + " -> " + items[1])

    # second index of the inner list
    print("Support: " + str(item[1]))

    # third index of the list located at 0th
    # of the third index of the inner list

    print("Confidence: " + str(item[2][0][2]))
    print("Lift: " + str(item[2][0][3]))
    print("=====================================")
