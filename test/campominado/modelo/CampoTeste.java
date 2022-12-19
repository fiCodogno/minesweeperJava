package campominado.modelo;

import campominado.excecao.ExplosaoException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Filipe
 */
public class CampoTeste {

    private final Campo campo = new Campo(3, 3);

    @Test
    public void testeVizinhoDistancia1() {
        Campo vizinho = new Campo(3, 2);
        
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    
    @Test
    public void testeVizinhoDistancia2() {
        Campo vizinho = new Campo(4, 2);
        
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }
    
    @Test
    public void testeNaoVizinho() {
        Campo vizinho = new Campo(1, 1);
        
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }
    
    @Test
    public void testeValorPadraoAtirbutoMarcado(){
        assertFalse(campo.isMarcado());
    }
    
    @Test
    public void testeAlternarMarcacao(){
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }
    
    @Test
    public void testeAlternarMarcacaoDuasChamadas(){
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }
    
    @Test
    public void testeAbrirNaoMinadoNaoMarcado(){
        assertTrue(campo.abrir());
    }
    
    @Test
    public void testeAbrirNaoMinadoMarcado(){
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }
    
    @Test
    public void testeAbrirMinadoMarcado(){
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }
    
    @Test
    public void testeAbrirMinadoNaoMarcado() {
        campo.minar();
        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }
    
    @Test
    public void testeAbrirComVizinhos1(){
        Campo campo11 = new Campo(1, 1);
        
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        
        campo.adicionarVizinho(campo22);
        campo.abrir();
        
        assertTrue(campo22.isAberto() && campo11.isAberto());
    }
    
    @Test
    public void testeAbrirComVizinhos2(){
        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();
        
        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);
        
        campo.adicionarVizinho(campo22);
        campo.abrir();
        
        assertTrue(campo22.isAberto() && !campo11.isAberto());
    }
}
