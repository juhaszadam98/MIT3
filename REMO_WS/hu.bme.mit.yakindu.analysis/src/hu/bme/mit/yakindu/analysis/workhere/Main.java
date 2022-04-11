package hu.bme.mit.yakindu.analysis.workhere;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		while (iterator.hasNext()) {
			int i = 1;
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				System.out.println("Állapot: " + state.getName());
				//2.4
				if (state.getOutgoingTransitions().isEmpty()) {
					System.out.println("Csapda állapot: " + state.getName());
				}
				//2.5
				if (state.getName().isEmpty()) {
					System.out.println("Javasolt név: State_" + i);
					i++;
				}
			} else if (content instanceof Transition) {
				Transition transition = (Transition) content;
				//2.3
				System.out.println("Tranzíció: " + transition.getSource().getName() + 
						" -> " + transition.getTarget().getName());
			}
		}
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
