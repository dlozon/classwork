import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_curve, auc, confusion_matrix
import matplotlib.pyplot as plt

class Loader:
    # Load data from a csv
    def loadData(self, fname):   
        '''
        Parameters:
            fname: a string containing the path to your data csv file
        Return:
            An array of headers (feature names)\n
            A 2d array containing your data without headers
        ''' 
        print("Loading Data..........")
        # Load the data
        arr = np.loadtxt(fname, delimiter=",", dtype=str)
        headers = arr[0, :]
        data = np.delete(arr, 0, 0)

        data = data.astype(int)
        
        # Print information about the data loaded
        print()
        print(f"Feature Names: \n{headers}\n")
        print(f"Data Loaded: \n{data}\n")
        print(f"Target Data: \n{data[:, -1]}\n")
        print(f"Correlation Coefficients Table:\n{np.corrcoef(np.transpose(data))}\n")
        print(f"Target Name: {headers[-1]}")
        print(f"Data Shape: {arr.shape}")
        print("\nData Loaded!")
        print()
        
        return headers, data
    
    # Split data into training and test predictors and outcomes
    def splitData(self, data, test_size=.2):
        '''
        Parameters:
            data: A 2d array containing your data
            test_size: the percentage of data that should be reserved for testing
        Return:
            The predictors to be used in model training\n
            The outcomes to be used in model training\n
            The predictors to be used in model testing\n
            The outcomes to be used in model testing
        ''' 
        # Split the data into training and test sets
        train, test = train_test_split(data, random_state=42, test_size=test_size)

        # Separate the predictors from the outcome
        trainX = train[:, :-1]
        trainY = train[:, -1]
        testX  =  test[:, :-1]
        testY  =  test[:, -1]

        return trainX, trainY, testX, testY 



class Preprocessor:
    def dropEmptyRows(self, data):
        '''
        Parameters:
            data: the data to have rows removed from
        Return:
            the new data with no empty rows
        ''' 
        print("\nRemoving empty rows of data...")

        rows, cols = data.shape
        newData = []
        removedRows = 0
        for row in range(rows):
            if np.all(data[row, :-1] == 0):
                removedRows += 1
            else:
                newData.append(data[row])

        print(f"{removedRows} rows of data were dropped.")
        return np.array(newData)




class Plotter:
    # Plot a histogram for one feature
    def plotFeatureHistogram(self, featureName, feature):
        '''
        Parameters:
            featureName: the name of the feature as a string
            feature: 1-dimensional array of data
        Function:
            Plot a histogram to show the distribution for feature
        '''
        # Plot the hist
        ignore, fig = plt.subplots()
        fig.set_title(f"{featureName} Measurements") 
        fig.set_xlabel(f'{featureName}')
        fig.set_ylabel('Frequency')
        fig.hist(feature, bins = 20,color = "blue",  edgecolor = "black")
        plt.show()



class Evaluator:
    # Print out the accuracy, recall, precision, specificity, and fstat of a model's
    def printMetrics(self, true, predicted):
        '''
        Parameters:
            true: the real data
            feature: what a model predicted
        Function:
            Provide information about a model's performance
        '''
        tn, fp, fn, tp = confusion_matrix(true, predicted).ravel()

        accuracy = (tp+tn) / (tp+tn+fp+fn)
        recall = tp / (tp+fn)
        precision = tp / (tp+fp)
        specificity = tn / (tn+fp)
        fstat = 2 * ((precision*recall) / (precision+recall))

        print(f"True Positives: {tp}, False Positives: {fp}, True Negatives: {tn}, False Negatives: {fn}")
        print(f"Accuracy: {accuracy}")
        print(f"Recall: {recall}")
        print(f"Precision: {precision}")
        print(f"Specificity: {specificity}")
        print(f"F-Stat: {fstat}")