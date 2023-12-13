import matplotlib.pyplot as plt
import numpy as np

#Code for Exercise 1
fig, fig1 = plt.subplots() 
print("\nExercise 1")
fig1.plot([1, 2, 4, 5, 6, 10], [3, 8, 1, 1, 6, 9]) 

#Code for Exercise 2
revx = np.linspace(0, 50, 100)
revy = np.linspace(0, 100, 100)
costx = np.linspace(0, 50, 100)
costy = np.linspace(20,60,100)
fig, fig2 = plt.subplots()
print("\nExercise 2")
fig2.plot(costx, costy,label = 'Cost') 
fig2.plot(revx, revy, label='Revenue') 
fig2.set_xlabel('Items Sold') 
fig2.set_ylabel('Dollars ($)') 
fig2.set_title("Cost Revenue Projection") 
fig2.legend() 
