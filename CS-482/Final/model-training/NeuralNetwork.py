import Helpers
import numpy as np
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import GridSearchCV


loader = Helpers.Loader()
evaluator = Helpers.Evaluator()

# Load and split the data
headers, data = loader.loadData("./data.csv")
rows, cols = data.shape
trainX, trainY, textX, testY = loader.splitData(data)

# Search for the best value of n_neighbors based on accuracy
model = MLPClassifier(max_iter=2000, random_state=42)
searchParam = "hidden_layer_sizes"
grid = {searchParam: range(1, 11, 1)}
searcher = GridSearchCV(model, grid, scoring='accuracy')
searcher.fit(trainX, trainY)

# Print the accuracy for each value of n_neighbors checked
means = searcher.cv_results_['mean_test_score']
params = searcher.cv_results_['params']
for mean, param in zip(means, params):
    print(f"{searchParam}: {param[searchParam]}, Accuracy: {mean}")
    
# Print the most accurate value
bestResult = searcher.best_params_[searchParam]
print(f"The best value of {searchParam} found was {bestResult}\n")

# Test the most accurate model on data it has not seen
model = searcher.best_estimator_
predictions = model.predict(textX)
# Print its test metrics
print(f"Best model results on test data:")
evaluator.printMetrics(testY, predictions)