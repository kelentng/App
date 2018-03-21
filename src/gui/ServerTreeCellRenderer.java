package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class ServerTreeCellRenderer implements TreeCellRenderer{
	private JCheckBox leafRenderer;
	private DefaultTreeCellRenderer nonLeafRenderer;
	private Color textBackground;
	private Color textForeground;
	private Color selectionBackground;
	private Color selectionForeground;
	public ServerTreeCellRenderer() {
		leafRenderer = new JCheckBox();
		textBackground = UIManager.getColor("Tree.textBackground");
		textForeground = UIManager.getColor("Tree.textForeground");
		selectionBackground = UIManager.getColor("Tree.selectionBackground");
		selectionForeground = UIManager.getColor("Tree.selectionForeground");
		nonLeafRenderer = new DefaultTreeCellRenderer();
		nonLeafRenderer.setLeafIcon(Utils.createIcon("/images/Server16.gif"));
		nonLeafRenderer.setOpenIcon(Utils.createIcon("/images/WebComponent16.gif"));
		nonLeafRenderer.setClosedIcon(Utils.createIcon("/images/WebComponentAdd16.gif"));
	}
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if(leaf) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			ServerInfo nodeInfo = (ServerInfo)node.getUserObject();
			if(selected) {
				leafRenderer.setBackground(selectionBackground);
				leafRenderer.setForeground(selectionForeground);
			}
			else {
				leafRenderer.setBackground(textBackground);
				leafRenderer.setForeground(textForeground);
			}
			leafRenderer.setText(nodeInfo.toString());
			leafRenderer.setSelected(nodeInfo.isChecked());
			return leafRenderer;
		}
		else {
			return nonLeafRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
	
}
