import Helpers
from sklearn.linear_model import LogisticRegression


loader = Helpers.Loader()
evaluator = Helpers.Evaluator()

# Load and split the data
headers, data = loader.loadData("./data.csv")
rows, cols = data.shape
trainX, trainY, textX, testY = loader.splitData(data)

# Train a logistic regressor to classify data
model = LogisticRegression(random_state=42, max_iter=2000)
model.fit(trainX, trainY)

# Test the model on data it has not seen before
predictions = model.predict(textX)
# Print its test metrics
print(f"Results on test data:")
evaluator.printMetrics(testY, predictions)