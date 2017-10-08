#####################
# Google's Intro TF #
# course.           #
#####################

# computational.py


# To compress warnings.
import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'



import tensorflow as tf

''' begin and end Function definitions  '''

def begin():
    with tf.Session() as sess:
        print('\ncomputational.py\n')
        
def end():
    with tf.Session() as sess:
        print('\nFin.\n')
 


''' Start of Program '''
begin()
# Simple Addition.
node1 = tf.constant(3.0, dtype=tf.float32)
node2 = tf.constant(4.0)

node3 = tf.add(node2, node1)

# Placeholder Example
a = tf.placeholder(tf.float32)
b = tf.placeholder(tf.float32)

node4 = 4 # placeholders work only for non-constant values.

adder_node = tf.add(a,b)     # Shortcuts to aid clean code.
adder_triple = adder_node*3

# Printing session for testing output
with tf.Session() as sess:
    print('node 3:', node3)
    print('sess.run(node 3) :', sess.run(node3))
    print('Expect (3+4)* 3 = 21. Result: ', sess.run(adder_triple, {a: 3, b: 4 } ))
    print('Expect 3+4 = 7. Result: ', sess.run(adder_node, {a: 3, b: 4 } ) )
    print('Expect 4+4 = 8. Result: ', sess.run(adder_node, {a: node4, b: node4 } ) )

#######################################
#
# Linear Regression Model (y = Wx + b)
#
#######################################
W = tf.Variable([.3], dtype=tf.float32)
b = tf.Variable([-.3], dtype=tf.float32)
x = tf.placeholder(tf.float32)
y = tf.placeholder(tf.float32)

linear_model = W*x + b
squared_deltas = tf.square(linear_model - y)
loss = tf.reduce_sum(squared_deltas)

init = tf.global_variables_initializer() # Initializa all variables in two steps. Step 2: sess.run(init) 

# Assign new values to the variables w and b
fixw = tf.assign(W, [-1])
fixb = tf.assign(b, [1.])


# Printing session for testing output
with tf.Session() as sess:
    sess.run(init) # Step 2 of initializing ariables
    sess.run([fixw, fixb]) # overwriting variable values.
    print('Output of Linear Model with x=[1,2,3,4] ==> ', sess.run(linear_model, {x: [1,2,3,4]}))
    print('Loss Function from Linear Model with x=[1,2,3,4], y=[0,-1,-2,-3] ==> ', sess.run(loss, {x: [1,2,3,4], y: [0,-1,-2,-3]}))



#########################################
#
#   Training. tf.Train API
#
#########################################
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)

with tf.Session() as sess:
    sess.run(init) # set W and b to default incorrect values
    
    for i in range(1000):
        sess.run( train, { x: [1,2,3,4], y: [0,-1,-2,-3] })
    print('Trained values for W and b ==> ', sess.run([W, b]))





end()
