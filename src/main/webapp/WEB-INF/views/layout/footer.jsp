    

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Modal de Excluir -->
<div id="modalExcluir" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Confirmar Exclusão</h4>
            </div>
            <div class="modal-body">

                <p>Deseja realmente excluir este item?</p>
                <p class="text-warning"><small>Após confirmar o item será excluído do banco de dados!</small></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancelar</button>
                <button class="btn btn-danger" onclick="Excluir();">Excluir</button>
            </div>
        </div>
    </div>
</div>  

<!-- jQuery -->
<script src="${baseURL}bootstrap/js/jquery.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${baseURL}bootstrap/js/bootstrap.min.js"></script>

<!-- date -->
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<!--select2-->
<script src="${baseURL}bootstrap/js/select2.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        $("span", this).toggleClass("glyphicon glyphicon-remove glyphicon glyphicon-menu-hamburger");
    });


    $(function() {
        $(".datepicker").datepicker({
                dateFormat: 'dd/mm/yy',
                dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
                dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
                dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
                monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
                monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
                nextText: 'Próximo',
                prevText: 'Anterior'
                
            });
        $(".datepicker").datepicker(); 
    });
    $(document).ready(function() {
        $(".js-example-basic-single").select2({
    minimumInputLength: 2,
    tags: [],
    ajax: {
        url: "http://localhost:8080/UFABC/area-restrita/treinamento/teste",
        dataType: 'json',
        type: "GET",
        quietMillis: 50,
        data: function (term) {
          return {                   
           term: term
          };
        },
        results: function (data) {
          return { 
           results: data  
          };
        }
 }
        });
    });

</script>
<script text="javascript">
    var codigo=0;
    var link="";
    
    function setCodigo(codigo){
        this.codigo=codigo;
    }
    
    function setLink(link){
        this.link=link;
    }
    
    function Excluir(){
        window.location=this.link+this.codigo;
    }   
</script>




