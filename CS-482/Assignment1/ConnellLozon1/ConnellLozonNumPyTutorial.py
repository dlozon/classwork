import numpy as np
import numpy.random as rg

#Code for Exercise 1
e1 = np.array([1,2,3,4])
print("\nExercise 1")
print(e1)
print(e1.dtype)

#Code for Exercise 2
b = np.zeros((2,7))
print("\nExercise 2")
print(b)

#Code for Exercise 3
c = np.arange(1,23,2.5)
print("\nExercise 3")
print(c)
print(c.dtype)

#Code for Exercise 4
d = np.arange(1,41,1).reshape(4,10)
d = d.reshape(5,8)
print("\nExercise 4")
print(d[4][7])

#Code for Exercise 5
arr = np.array([[[1,2],[1,2],[1,2]], [[1,2],[1,2],[1,2]],  [[1,2],[1,2],[1,2]]])
print("\nExercise 5")
print(arr)
print(arr.ndim)
print(arr.shape)
print(arr.size)
print(arr.dtype)

#Code for Exercise 6
m = rg.random((4,4))
p = rg.random((4,4))
k = m.sum(axis = 1)
print("\nExercise 6")
print(np.sin(m) @ np.cos(p) + k)

#Code for Exercise 7
rg.seed(42)
f1 = rg.random(10)
f1 = np.sort(f1)
print("\nExercise 7")
print("Smallest: ", f1[0])
print("Largest: ", f1[9])
f2 = f1.reshape(2,5)
f3 = f2
f4 = f2
f5 = np.concatenate((f3, f4), axis = 1)
print(f5)
