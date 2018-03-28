import math

def readData(filename):
    # open the csv file
    f = open(filename, 'rU')
    data=[]
    # read each transaction line by line from file
    for row in f:
        # stip end element of new-line and make list of all element present in transaction
        # add all transaction as list into a list
        data.append(row.strip('\n').split(','))
    # return the data
    return data

def gain_catgory(col, dataset):
    total = list(row[col] for row in dataset)

    len_total = len(total)
    
    # all values of column and there frequency 
    Att_dict = {}
    for i in total:
        if i in Att_dict:
            Att_dict[i] +=1
        else:
            Att_dict[i] = 1

    # Calculaeing gain
    gain = 0
    for i in Att_dict.keys():
        p = (Att_dict[i]/len_total)
        gain += -  p * math.log(p,2)
        
    return gain


def gain_Attribute_catgory(col, dataset):

    gain = 0
    p=0
    classes = list(set(row[col] for row in dataset))
    for i in classes:
        
        len_total = len(dataset)

        new_dataset = [row for row in dataset if i == row[col]]

        
        new_len_total = len(new_dataset)

        p += (new_len_total/len_total) * gain_catgory(len(dataset[0])-1,new_dataset)


    return p


# Select the best split point for a dataset
def split_Attribute(dataset):
    
    # get list of classes
    # use set to make remove reducatnt
    # dataset[0] is atttribute value
    classes = list(set(row[-1] for row in dataset[1:]))


    info_gain = gain_catgory(4,dataset[1:])
    #print("info_gain: ",info_gain)

    best_gain = 0
    for col in range(len(dataset[0])-1):
        
        # Get gain for this new subdataset
        gainA = gain_Attribute_catgory(col, dataset[1:])

        dgain = info_gain - gainA

        # if new gainA is better then update and also in case we need inital update
        if dgain > best_gain or best_gain ==0:
            b_col, best_gain = col, dgain

    #print(dataset[0][b_col])
        
    links = list(set(val[b_col] for val in dataset[1:]))
    ret = {}
    for i in links:
        ret[i] = [dataset[0]] +  [row for row in dataset if i == row[b_col]]
    
    return {'b_col':b_col , 'best_gain':best_gain, 'data':ret}

# check if sub_dataset contains only one label then purne
def purne(sub_dataset):
    outcomes = [row[-1] for row in sub_dataset]
    max_val = max(set(outcomes), key=outcomes.count)

    for i in sub_dataset:
        if i[-1] != max_val:
            return False,max_val
    return True,max_val

# Create a terminal node value
def to_terminal(sub_dataset):
	outcomes = [row[-1] for row in sub_dataset[1:]]
	return max(set(outcomes), key=outcomes.count)


def split(node, max_depth, min_size, depth):
    ret = node['data']
    # remove the key:value
    del(node['data'])

    for i in ret.keys():

        check = purne(ret[i])
        if check[0]:
            node[i] = check[1]
            continue
        
        if depth >= max_depth:
            node[i] = to_terminal(ret[i])
            continue

        if len(ret[i]) <= min_size:
            node[i] = to_terminal(ret[i])
        else:
            node[i] = split_Attribute(ret[i])
            split(node[i], max_depth, min_size, depth+1)

 

def CreateTree(data):

    root = split_Attribute(data)
    split(root,5,1,1)

    return root
def printTree(root, n = 1):


    val = root.keys() - ['b_col','best_gain']
    
    for i in val:
        
        if type(root[i])==type('str'):
            print("level : "+str(n),root[i] , root['best_gain'], root['b_col'],"\n")
        else:
            print("level : "+str(n),i , root['best_gain'], root['b_col'])
            printTree(root[i],n+1)
        if n==1:
            print("\n\n")
    
data = readData("DTree.csv")


#print(split_Attribute(data))
#print(to_terminal(data))

root = CreateTree(data)
#print(root)

printTree(root)

