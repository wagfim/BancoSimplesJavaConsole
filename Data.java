package iniciante.banco;
/**
 * @author Wagner Bonfim <github.com/wagfim>
 */
public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    public String getDataFormatada() {
        String diaString = Integer.toString(dia);
        String mesString = Integer.toString(mes);
        if (dia < 10) {
            diaString = "0" + dia;
        }
        if (mes < 10) {
            mesString = "0" + mes;
        }
        return diaString + "/" + mesString + "/" + ano;
    }
}
