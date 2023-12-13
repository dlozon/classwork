import Helpers
import numpy as np
from sklearn.tree  import DecisionTreeClassifier
from sklearn.model_selection import GridSearchCV


loader = Helpers.Loader()
evaluator = Helpers.Evaluator()

# Load and split the data
headers, data = loader.loadData("./data.csv")
rows, cols = data.shape
trainX, trainY, textX, testY = loader.splitData(data)

# Search for the best value of n_neighbors based on accuracy
model = DecisionTreeClassifier(random_state=42)
searchParam = "max_depth"
searchParam2 = "min_samples_leaf"
grid = {searchParam: [None] + list(range(1, 11, 1)), searchParam2: range(1, 21, 2)}
searcher = GridSearchCV(model, grid, scoring='accuracy')
searcher.fit(trainX, trainY)

# Print the accuracy for each value of n_neighbors checked
means = searcher.cv_results_['mean_test_score']
params = searcher.cv_results_['params']
for mean, param in zip(means, params):
    print(f"{searchParam}: {param[searchParam]}, {searchParam2}: {param[searchParam2]}, Accuracy: {mean}")
    
# Print the most accurate values
bestResult = searcher.best_params_[searchParam]
print(f"The best value of {searchParam} found was {bestResult}")
bestResult2 = searcher.best_params_[searchParam2]
print(f"The best value of {searchParam2} found was {bestResult2}\n")

# Test the most accurate model on data it has not seen
model = searcher.best_estimator_
predictions = model.predict(textX)
# Print its test metrics
print(f"Best model results on test data:")
evaluator.printMetrics(testY, predictions)