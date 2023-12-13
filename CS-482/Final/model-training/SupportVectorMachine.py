import Helpers
import numpy as np
from sklearn.svm import SVC
from sklearn.model_selection import GridSearchCV


loader = Helpers.Loader()
evaluator = Helpers.Evaluator()

# Load and split the data
headers, data = loader.loadData("./data.csv")
rows, cols = data.shape
trainX, trainY, textX, testY = loader.splitData(data)

# Train an SVM to classify data
model = SVC(random_state=42)
model.fit(trainX, trainY)

predictions = model.predict(textX)
# Print its test metrics
print(f"Best model results on test data:")
evaluator.printMetrics(testY, predictions)