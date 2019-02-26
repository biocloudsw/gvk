package cn.big.gvk.po.tree;

public class NodeChild {
	private String id;
	private String text;
	private boolean children;
	
	private NodeData data;
	private NodeliAttr li_attr;
	private NodeState nodeState;
	
	public NodeState getNodeState() {
		return nodeState;
	}
	public void setNodeState(NodeState nodeState) {
		this.nodeState = nodeState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChildren() {
		return children;
	}
	public void setChildren(boolean children) {
		this.children = children;
	}
	public NodeData getData() {
		return data;
	}
	public void setData(NodeData data) {
		this.data = data;
	}
	public NodeliAttr getLi_attr() {
		return li_attr;
	}
	public void setLi_attr(NodeliAttr li_attr) {
		this.li_attr = li_attr;
	}
	
	
}
