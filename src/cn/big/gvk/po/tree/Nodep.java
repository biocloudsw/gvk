package cn.big.gvk.po.tree;

import java.util.List;

public class Nodep {
	private String id;
	private String text;
	private List<Nodep> children;
	
	private NodeState state;
	private NodeData data;
	private NodeliAttr li_attr;
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
	public List<Nodep> getChildren() {
		return children;
	}
	public void setChildren(List<Nodep> children) {
		this.children = children;
	}
	public NodeState getState() {
		return state;
	}
	public void setState(NodeState state) {
		this.state = state;
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
