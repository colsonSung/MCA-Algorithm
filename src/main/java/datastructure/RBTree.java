package datastructure;

/**
* add node
* delete node
* search node
* */
public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        this.root = root;
    }

    // 1. left&right rotate and change color
    // 2. add node: BST insert+ self balance
    // 3. delete node
    // 4. traversal tree

    /**left rotate
    *     /              /
    *    p(旋转节点)      pr
    *   /\              /\
    * pl  pr    -->    p  rr
    *     /\          /\
    *    rl rr       pl rl
    * */
    public void leftRotate(RBNode p){
        if(p != null){
            RBNode pr = p.right;
            p.right = pr.left;
            if(pr.left !=null){
                pr.left.parent = p; //rl point to p
            }

            pr.parent = p.parent;
            if(p.parent == null){
                this.root = pr;
            } else if (p.parent.left == p) {
                p.parent.left = pr;
            } else{
                p.parent.right = pr;
            }

            pr.left = p;
            p.parent = pr;
        }
    }

    /**right rotate
     *         /              /
     *         p(旋转节点)      pl
     *        /\              / \
     *      pl  pr    -->    ll  p
     *     / \                  / \
     *    ll  lr               lr  pr
     * */
    public void rightRotate(RBNode p){
        if(p !=null){
            RBNode pl = p.left;
            p.left = pl.right;
            if(pl != null){
                pl.right.parent = p;
            }

            pl.parent = p.parent;
            if(p.parent==null){
                this.root = pl;
            } else if (p.parent.right == p) {
                p.parent.right = pl;
            }else{
                p.parent.left = pl;
            }

            pl.right = p;
            p.parent = pl;
        }
    }

    /**
     * 新增一个节点
     * @param k
     * @param v
     */
    public void put(K k, V v){
        //1. find the position to insert
        RBNode<K,V> t = root;
        if(t == null){
            t = new RBNode<>(k,v,null);
            setRoot(t);
            return;
        }

        RBNode parent; //找到插入节点的父节点
        int cmp; //记录最后一次是左还是右？
        do{
            parent = t;
            cmp = k.compareTo((K)t.key);
            if(cmp <0){
                t = parent.left;
            } else if(cmp > 0){
                t= parent.right;
            } else {
                t.setValue(v);
                return;
            }
        } while(t != null);

        RBNode node = new RBNode(k,v,parent);
        if(cmp < 0){
            parent.left = node;
        } else{
            parent.right = node;
        }

        //2. rotate and change color to remain balance
        fixAfterInsert(node);  //自底向上的自平衡过程
    }

    /**
     * 红黑树（二叉树）的某个节点所处的情况总能映射到2-3-4树2节点、3节点、4节点其中某一种情况对应起来
     * 情况1：当前节点是不包含子节点的节点，对应于2-3-4树的的2节点。此时新增一个节点时：
     *      2-3-4树：2节点变成3节点
     *      红黑树：拆解后，相当于黑节点下新增了一个红节点，无需额外处理
     *
     * 情况2: 当前节点是包含1个子节点的节点，对应于2-3-4树的3节点。此时新增一个节点时：
     *      2-3-4树：插入一个节点后，变成4节点，包含3个元素
     *      红黑树：插入新节点并按2-3-4树拆解后，包含六种情况 根左左(new)、根左右(new)、根右右(new)、
     *             根右左(new)、左(new)根右、左根右(new),最后两种无需调整，前四种情况描述如下：
     *             n
     *             /
     *            l
     *           /
     *          new
     *          根左左(new)：一次右旋+变色
     *
     *           n
     *            \
     *             r
     *              \
     *              new
     *          根右右(new)：一次左转+变色：
     *
     *            n
     *           /
     *          l
     *           \
     *           new
     *          根左右(new)：先l左旋后n右旋+变色
     *
     *           n
     *            \
     *             r
     *            /
     *           new
     *          根右左(new)：先r右旋后n左旋+变色
     *
     * 情况3: 当前节点是包含2个子节点的节点，对应于2-3-4树的4节点。此时新增一个节点时：
     *      2-3-4树：将发生裂变，中间节点提升为父节点，新节点和子节点合并
     *      红黑树：新节点总是挂在子节点上，之后只需做变色即可，实际有以下4种情况
     *         n             n           n             n
     *        / \           / \         / \           / \
     *       l   r         l   r       l   r         l   r
     *      /              \              /              \
     *     new             new          new              new
     *
     * 注：新插入的节点，需始终是红节点，因为一旦插入的节点是黑色，那一插入便会破坏平衡。
     *
     * 综合上述三种情况，编码时可以按照左子树和右子树来划分所包含的情况
     */
    private void fixAfterInsert(RBNode<K, Object> n){
        while(n != null && n != root && n.parent.color == RED){  //n.parent.color == BLACK时相当于注解中情况1，无需处理
            if(parentOf(parentOf(n)).left == parentOf(n)){ //这里判断是在爷爷节点的左侧还是右侧，后续将会涉及到爷爷节点的旋转
                RBNode y = parentOf(parentOf(n)).right;
                //再判断是情况2 还是情况3
                if(colorOf(y) == RED){
                    //情况3前两种场景,自低向上进行变色处理即可
                    setColor(parentOf(n), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(n)), RED);

                    n = parentOf(parentOf(n)); //爷爷节点变为红色，之后需要将爷爷节点及其子节点看作一个大的红色节点进行向上递归
                } else {
                    //情况2，叔叔节点不存在
                    if(n == parentOf(n).right){//情况2中的“根左右(new)”
                        n = parentOf(n);
                        leftRotate(n);
                    }
                    //根左右(new)一次左旋之后就变成了情况2的“根左左”
                    setColor(parentOf(parentOf(n)), RED);
                    setColor(parentOf(n), BLACK);
                    rightRotate(parentOf(parentOf(n)));
                }
            } else {
                RBNode y = parentOf(parentOf(n)).left;
                //再判断是情况2 还是情况3
                if(colorOf(y) == RED){
                    //情况3前两种场景,自低向上进行变色处理即可
                    setColor(parentOf(n), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(n)), RED);

                    n = parentOf(parentOf(n)); //爷爷节点变为红色，之后需要将爷爷节点及其子节点看作一个大的红色节点进行向上递归
                } else {
                    //情况2，叔叔节点不存在
                    if(n == parentOf(n).left){//情况2中的“根右左(new)”
                        n = parentOf(n);
                        rightRotate(n);
                    }
                    //根右左(new)一次右旋之后就变成了情况2的“根右右”
                    setColor(parentOf(parentOf(n)), RED);
                    setColor(parentOf(n), BLACK);
                    leftRotate(parentOf(parentOf(n)));
                }
            }
        }
        setColor(root, BLACK);  //最后，根节点始终是黑色
    }

    private void setColor(RBNode n, boolean color) {
        if(n != null){
            n.setColor(color);
        }
    }

    private boolean colorOf(RBNode y) {
        return y == null? BLACK : y.color;
    }

    private RBNode parentOf(RBNode<K, Object> n) {
        return n != null? n.parent : null;
    }

    /**
     * 红黑树的删除
     *    1. 首先是一般二叉树删除节点的操作，分以下三种情况
     *      1. 被删除节点是叶子节点，直接删除即可
     *      2. 被删除节点有一个子节点，用子节点替代被删除节点即可
     *      3. 被删除节点有两个子节点，找到其前驱/后继节点进行替代（并非一定是左/右子节点）
     *         注：前驱节点和后继节点，若存在子节点，只可能存在一个子节点；
     *    2. 再通过旋转和变色实现平衡
     * @param key
     * @return
     */
    public V remove(K key){
        RBNode node = findNode(key);

        V oldValue = (V) node.getValue();
        deleteNode(node);
        return oldValue;

    }

    private RBNode findNode(K k){
        RBNode n = this.root;
        while(k != null && n != null){
            int cmp = k.compareTo((K) n.getKey());
            if(cmp < 0){
                n = n.left;
            } else if(cmp >=0 ){
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    private void deleteNode(RBNode node) {
        if(node.left != null && node.right != null){
            //对应于情况3 找到被删除节点的前驱节点，并用其进行替代
            RBNode predecessor = findPredecessor(node);
            node.key = predecessor.getKey();
            node.value = predecessor.getValue();

            //替代完成后，将node指向前驱节点，之后就是情况2/情况1
            node = predecessor;
        }

        RBNode childNode = node.getLeft() != null ? node.getLeft() : node.getRight();
        if(childNode != null){
            //condition 2: 先删除node，使用其childNode替换后，根据childNode进行平衡调整
            if(node.parent == null){
                this.root = childNode;
            } else if (parentOf(node).left == node) {
                parentOf(node).left = childNode;
            } else {
                parentOf(node).right = childNode;
            }
            childNode.parent = parentOf(node);
            node.parent = node.left = node.right = null;
            
            if(node.color == BLACK){
                fixAfterDelete(childNode);
            }
        } else {
            //condition 1: 直接根据node进行平衡调整，然后再删除
            if(node.color == BLACK){
                fixAfterDelete(node);
            }

            if(node.parent == null){
                root = null;
            } else if(node.parent.left == node){
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    /**
     *
     * @param node
     */
    private void fixAfterDelete(RBNode node) {
        //黑色节点的情况下才需要进行平衡调整
        if(node != root && node.color == BLACK){

        }

        //
    }

    private RBNode findPredecessor(RBNode node) {
        if(node == null){
            return  null;
        }

        if (node.left != null){
            RBNode p = node.left;
            while(p.right != null){
                p = p.right;
            }
            return p;
        } else {
            // 这种情况在删除中是不会出现的，但是对于查找前驱节点来说我们还是要来实现的一种情况
            // 就是如果当前节点没有左子节点，那么我们就需要向上找
            RBNode p = node.parent;
            RBNode ch = node;
            while(p != null && ch == p.left){
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    static class RBNode<K extends Comparable<K>,V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;

        private boolean color; //true=black; false=red

        private Object key;
        private Object value;

        RBNode() {

        }

        public RBNode(Object key, Object value,boolean color) {
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode(Object key, Object value, RBNode parent) {
            this.parent = parent;
            this.key = key;
            this.value = value;
        }

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, Object key, Object value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
