package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controller.MessageServer;
import model.Message;

class ServerInfo{
	private String name;
	private int id;
	private boolean checked;
	public ServerInfo() {
		super();
	}
	public ServerInfo(String name, int id, boolean checked) {
		super();
		this.name = name;
		this.id = id;
		this.checked = checked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String toString() {
		return name;
	}
}
public class MessagePanel extends JPanel implements ProgressDialogListener{
	private JTree serverTree;
	private TextPanel textPanel;
	private JList messageList;
	private DefaultListModel messageListModel;
	private JSplitPane upperPane;
	private JSplitPane lowerPane;
	private ServerTreeCellRenderer treeCellRenderer;
	private ServerTreeCellEditor treeCellEditor;
	private Set<Integer> selectedServers;
	private MessageServer messageServer;
	private ProgressDialog progressDialog;
	private SwingWorker<List<Message>,Integer> worker;
	public MessagePanel(JFrame parent) {
		progressDialog = new ProgressDialog(parent,"Message Downloading...");
		progressDialog.setListener(this);
		messageServer = new MessageServer();
		selectedServers = new TreeSet<Integer>();
		selectedServers.add(0);
		selectedServers.add(1);
		selectedServers.add(4);
		treeCellRenderer = new ServerTreeCellRenderer();
		treeCellEditor = new ServerTreeCellEditor();
		serverTree = new JTree(createNode());
		serverTree.setCellRenderer(treeCellRenderer);
		serverTree.setCellEditor(treeCellEditor);
		serverTree.setEditable(true);
		
		serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		messageServer.setSelectedServers(selectedServers);
		treeCellEditor.addCellEditorListener(new CellEditorListener() {
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				
			}
			@Override
			public void editingStopped(ChangeEvent arg0) {
				ServerInfo info = (ServerInfo)treeCellEditor.getCellEditorValue();
				
				if(info.isChecked()) {
					selectedServers.add(info.getId());
				}
				else {
					selectedServers.remove(info.getId());
				}
				
				messageServer.setSelectedServers(selectedServers);
				
				
				
				
				retrieveMessages();
			}
			
			
		});
		
		textPanel = new TextPanel();
		messageListModel = new DefaultListModel();
		messageList = new JList(messageListModel);
		lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(messageList),textPanel);
		upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(serverTree),lowerPane);
		textPanel.setMinimumSize(new Dimension(10,100));
		messageList.setMinimumSize(new Dimension(10,100));
		upperPane.setResizeWeight(0.5);
		lowerPane.setResizeWeight(0.5);
		setLayout(new BorderLayout());
		add(upperPane,BorderLayout.CENTER);
	}
	
	public void refresh() {
		retrieveMessages();
		}
	
	private void retrieveMessages() {
		progressDialog.setMaximum(messageServer.getMessageCount());
		progressDialog.setVisible(true);
		
		worker = new SwingWorker<List<Message>,Integer>(){

			
			@Override
			protected void done() {
				progressDialog.setVisible(false);
				if(isCancelled()) return;
				try {
					List<Message> retrieveMessages = get();
					messageListModel.removeAllElements();
					for(Message message:messageServer) {
						messageListModel.addElement(message.getTitle());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			protected void process(List<Integer> counts) {
				int retrieved = counts.get(counts.size()-1);
				progressDialog.setValue(retrieved);
			}

			@Override
			protected List<Message> doInBackground() throws Exception {
				List<Message> retrieveMessages = new ArrayList<Message>();
				int count = 0;
				for(Message message:messageServer) {
					if(isCancelled()) break;
					System.out.println(message.getTitle());
					retrieveMessages.add(message);
					count++;
					publish(count);
					}
				return retrieveMessages;
			}
			
		};
		worker.execute();
	}
	
	private DefaultMutableTreeNode createNode() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("New York",0,selectedServers.contains(0)));
		DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Boston",1,selectedServers.contains(1)));
		DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("Los Angeles",2,selectedServers.contains(2)));
		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",3,selectedServers.contains(3)));
		DefaultMutableTreeNode server5 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh",4,selectedServers.contains(4)));
		
		branch1.add(server1);
		branch1.add(server2);
		branch1.add(server3);
		
		branch2.add(server4);
		branch2.add(server5);
		
		top.add(branch1);
		top.add(branch2);
		
		return top;
		
		
	}

	@Override
	public void progressDialogCancelled() {
		worker.cancel(true);
	}
}
