package fr.demos.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import fr.demos.web.FirstServlet;

public class TestFirstServlet {
	
@Test	
public void testDoGet() throws Exception {

	// on pr�pare les mocks n�cessaires pour le lancement de la servlet (en dehors de son environnement)
	// ce sont des classes qui font semblant d'ex�cuter le comportement de la classe initiale
	HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockResponse = mock(HttpServletResponse.class);
    PrintWriter mockPrintWriter = mock(PrintWriter.class);
    
    // on construit le commportement attendu
    // par d�faut les mocks ne font rien, les m�thodes de la classe initiale existent mais sont sans comportement dans le mock
    // l'int�r�t d'outils comme Mockito : on peut configurer le comportement des m�thodes dont on a besoin pour le test
    // le mock est programm� en fonction de ce que le test attend
    // Pas la peine de tout programmer. Pas la peine d'�crire une fausse classe. Tout est dynamique 
    when(mockRequest.getParameter("nom")).thenReturn("Dupont");
    when(mockRequest.getParameter("prenom")).thenReturn("Pierre");
    when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
    // si on voulait utiliser une jsp
    //when(mockRequest.getRequestDispatcher("/first.jsp")).thenReturn(mock(RequestDispatcher.class));  
    
    // on lance la Servlet
    new FirstServlet().doGet(mockRequest, mockResponse);

    // on v�rifie que la Servlet a bien ex�cut� les m�thodes voulues avec les bonnes informations
    // Mockito peut le faire car il peut tracer l'ex�cution de toutes les m�thodes du mock
    // verify(mockRequest).setAttribute("nomcomplet","Dupont Pierre"); 
    verify(mockResponse).setContentType("plain/text");
    verify(mockPrintWriter).println("Dupont Pierre");	
	
}
	
	

}
