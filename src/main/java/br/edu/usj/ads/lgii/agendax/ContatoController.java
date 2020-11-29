package br.edu.usj.ads.lgii.agendax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {
   @Autowired 
   ContatoRepository contatoRepository;
   @GetMapping(value="/") //raiz
   public ModelAndView getListaTodos() {//raiz
       List<Contato> lista = contatoRepository.findAll();//raiz

       ModelAndView modelAndView = new ModelAndView("index");//raiz
       modelAndView.addObject("lista", lista);//raiz
       return modelAndView;  //raiz
   }
   
   // Detalhes-html -Criando um Get Mapping para apontar para a pagina detalhe HTML
   @GetMapping(value="/detalhes/{id}")//aponta a URL
   public ModelAndView getMostrarPorId(@PathVariable Long id) {
       //ler do banco
       Contato contato = contatoRepository.findById(id).get();

       ModelAndView modelAndView = new ModelAndView("detalhes");
       //acresecentar na modelAndView
       modelAndView.addObject("contato" ,contato);
       return modelAndView;
   }

   @GetMapping(value="/cadastro")
   public ModelAndView getCadastro() {

Contato contato = new Contato();

       ModelAndView modelAndView = new ModelAndView("cadastro");
modelAndView.addObject("contato", contato);
       return modelAndView;
   }

   @PostMapping(value="/adicionar")
   //public ModelAndView postAdicionarContato(Contato contato){}
   public  String  postAdicionaContato(Contato contato) {  //nao vou redicionar uma modelAandview e sim uma String
       contatoRepository.save(contato);
       return "redirect:/"; 
   }
   ///ao inves de usar o thymeleaf uso o conceito de request response

//Desta forma estou retornando um request sem renderizar a view, mas avisandp navegador recarrega a raiz
//O return acima otimizou o que estava abaixo - troquei esta fiaxa abaixo e coloquei a linha 36 e 37 para simplificar o POs, esta voltado para a rraiz direto sem renderizar
  // List<Contato> lista = contatoRepository.findAll();//*usando toda a esturuttura(thymeleaf/engine)
       //ModelAndView modelAndView = new ModelAndView("index");//*usando...
       //modelAndView.addObject("lista", lista);//*usannndo
       //return modelAndView;//*usando....

   //DELETAR
       @GetMapping(value="/deletar/{id}")
public String getDeletar(@PathVariable Long id) {
   contatoRepository.deleteById(id);
return "redirect:/";
}
   //EDITAR
@GetMapping(value="/editar/{id}")
public ModelAndView getEditar(@PathVariable Long id) {
Contato contato = contatoRepository.findById(id).get();

   ModelAndView modelAndView = new ModelAndView("cadastro");
   modelAndView.addObject("contato", contato);
   return  modelAndView;
}
   //PESQUISAR renderizar um html
@GetMapping(value="/pesquisar") //retorna pesquisa
public String getPesquisar() { //Sem param pq tenho que renderizar só o HTML
   return "pesquisar"; //qdo nao preciso adicinar um objeto, posso retornar o nome da view(pesquisar), procura um arquivo  pesquisar html  e retornar ao usuário
}

@PostMapping(value="pesquisar")
public ModelAndView postPesquisar(@RequestParam String nome) {
    List<Contato>lista = contatoRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);//para pesquisar meio uso este find

    ModelAndView modelAndView = new ModelAndView("pesquisar");
    modelAndView.addObject("nome", nome);
    modelAndView.addObject("lista", lista);
    return modelAndView;
}


}


