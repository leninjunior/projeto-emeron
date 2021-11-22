package reserva.emeron.projetoemeron.controller;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reserva.emeron.projetoemeron.model.PeriodoRelatorio;
import reserva.emeron.projetoemeron.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {



    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/gerar")
    public ModelAndView relatorioVendasEmitidas() {
        ModelAndView mv = new ModelAndView("relatorio/relatorioreservado.html");
        mv.addObject(new PeriodoRelatorio());
        return mv;
    }

    @PostMapping("/reservado")
    public ResponseEntity<byte[]> gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) {

        try {
            byte[] relatorio = relatorioService.gerarRelatorioVendasEmitidas(periodoRelatorio);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatorio);

        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }


    }

}

    

    	
	
	
	
	


