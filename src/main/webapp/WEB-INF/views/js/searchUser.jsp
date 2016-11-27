<%-- 
    Document   : searchUser
    Created on : 27/11/2016, 12:11:12
    Author     : rafin
--%>
<script type="text/javascript">
    $(document).ready(function() {
        //http://localhost:8080/UFABC/area-restrita/alocacao/listartudo
        $("#busca").keyup(function() {
            var nome = $('input:text[name=busca]').val();
            $.post("${baseURL}area-restrita/usuario/buscarPeloNome",
                    {
                        name: nome,
                    },
                    function(data) {
                        //$('.pessoas').empty();
                        
                        $('.pessoas').html(data);
                        //alert(data);
                    });
        });

    })
</script>