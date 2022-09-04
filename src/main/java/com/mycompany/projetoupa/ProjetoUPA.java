/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.projetoupa;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author gabri
 */
public class ProjetoUPA {
    // Define o array q vai guardar todos os Municipios
    static List<Municipio> listMunicipios = new ArrayList<Municipio>();
    // Array que vai guardar todos os objetos UPA
    static List<UPA> listUPA = new ArrayList<UPA>();
    
    public static void downloadResources(){
        final String httpFile = "https://geoftp.ibge.gov.br/organizacao_do_territorio/estrutura_territorial/divisao_territorial/2021/DTB_2021.zip";
        final String localFile = "./DTB_2021 (1).zip";
        
        try {
            URL website = new URL(httpFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(localFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void getAllMunicipios(){    
        // Variaveis para instanciar os Municipios
        String UF = null;
        String Nome_UF = null;
        String Reg_Geo_Inter = null;
        String Nome_Reg_Geo_Inter = null;
        String Reg_Geo_Imed = null;
        String Nome_Reg_Geo_Imed = null;
        String Mes_Geo = null;
        String Nome_Mes_Geo = null;
        String Mic_Reg_Geo = null;
        String Nome_Mic_Reg_Geo = null;
        String Municipio = null;
        String Cod_Muni_Comp = null;
        String Nome_Munic = null;
        // Quantidade de Municipios Salvos
        Integer QttMuni = 0;
        
        try {
            // Caminho do Arquivo a ser lido
            FileInputStream fis = new FileInputStream("RELATORIO_DTB_BRASIL_MUNICIPIO.xls");
            // Salva a planilha a ser Lida
            HSSFWorkbook planilha = new HSSFWorkbook(fis);
            // Define que pag da Planilha vai ser lida
            HSSFSheet relatorio = planilha.getSheetAt(0);
            // Instancia um leitor de linhas
            Iterator<Row> itLinha = relatorio.iterator();
            // Enquanto tiver linha
            while(itLinha.hasNext()){
                // Proxima Linha
                Row linha = itLinha.next();
                // Instancia um leitor de colunas
                Iterator<Cell>cellIt = linha.cellIterator();
                // Enquanto tiver coluna nessa linha
                while(cellIt.hasNext()){
                    // Le uma coluna
                    Cell celula = cellIt.next();
                    // Decide oque fazer com cada valor de cada coluna da linha
                    switch(celula.getColumnIndex()){
                        // Se for da coluna[0] -> salva na variavel UF
                        case 0: 
                            UF = celula.getStringCellValue();
                            break;
                        // Se for da coluna[1] -> salva na variavel Nome_UF ...
                        case 1:
                            Nome_UF = celula.getStringCellValue();
                            break;
                        case 2:
                            Reg_Geo_Inter = celula.getStringCellValue();
                            break;     
                        case 3:
                            Nome_Reg_Geo_Inter = celula.getStringCellValue();
                            break;
                        case 4:
                            Reg_Geo_Imed = celula.getStringCellValue();
                            break;
                        case 5:
                            Nome_Reg_Geo_Imed = celula.getStringCellValue();
                            break;
                        case 6:
                            Mes_Geo = celula.getStringCellValue();
                            break;
                        case 7:
                            Nome_Mes_Geo = celula.getStringCellValue();
                            break;
                        case 8:
                            Mic_Reg_Geo = celula.getStringCellValue();
                            break;
                        case 9:
                            Nome_Mic_Reg_Geo = celula.getStringCellValue();
                            break;    
                        case 10:
                            Municipio = celula.getStringCellValue();
                            break;    
                        case 11:
                            Cod_Muni_Comp = celula.getStringCellValue();
                            break;
                        case 12:
                            Nome_Munic = celula.getStringCellValue();
                            break;
                    }
                }
                // Instancia um novo municipio com os valores lidos anteriormente
                Municipio muni = new Municipio(UF, Nome_UF, Reg_Geo_Inter, Nome_Reg_Geo_Inter, Reg_Geo_Imed, Nome_Reg_Geo_Imed, Mes_Geo, Nome_Mes_Geo, Mic_Reg_Geo, Nome_Mic_Reg_Geo, Municipio, Cod_Muni_Comp, Nome_Munic);
                // Adiciona o munipicio instanciado a lista de municipios
                listMunicipios.add(muni);
                // +1 em quantidade de municipios
                QttMuni ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Apaga o primeiro valor da tabela, porque é o head do XML
        listMunicipios.remove(0);
        System.out.println("Municipios: ");
        System.out.println("<------------------------------>");
        // Printa todos os Municipios
        for(Municipio p : listMunicipios){   
            System.out.println(p);
        }
        // Printa a quantidade de Municipios lida <5571>
        System.out.println("Qtt Municipios: " + QttMuni);
        
     }
    
    public static List<String> findInMunicipios(String NomeMunicipio){
        // Cria um Array para os municipios que vao ser filtrados
        List<Municipio> MunicipiosFiltrados = new ArrayList<Municipio>();
        // Printa o municipio que vai ser filtrado
        System.out.println("Municipio Pesquisado: " + NomeMunicipio);
        // Para cada objeto Municipio p em listMunicipios
        for(Municipio p : listMunicipios){
            // Verifica se o municipo p na variavel Nome_Munic é igual ao municipio a ser pesquisado
            if(p.getNome_Munic().equals(NomeMunicipio)){
                // Se for igual -> salva esse objeto municipio em MunicipiosFiltrados
                System.out.println("Dentro do If vei");
                MunicipiosFiltrados.add(p);
                System.out.println("Boolean = True");
            }   else{
                System.out.println("Boolean = False");
                System.out.println("p.getNome_Munic = " + p.getNome_Munic() + " || NomeMunicipio = " + NomeMunicipio);
                System.out.println("UF: " + p.getNome_UF() +" || Nome do Municipio: " + p.getNome_Munic());
            }
           
        }
        System.out.println("Nome do Municipio a ser Pesquisado: " + NomeMunicipio);
        System.out.println("<=============================================>");
        System.out.println("Inicio dos Municipios Filtrados");
        List<String> ListDoIBGE = new ArrayList<String>();
        for(Municipio p : MunicipiosFiltrados){
            ListDoIBGE.add(p.getCod_Muni_Comp());
            System.out.println("Codigo do Municipio: " + p.getCod_Muni_Comp());
        }
        return ListDoIBGE;        
    }
    
    public static void getAllUPAS(){
        // Quantidade de UPAs lidas
        Integer QttUpa = 0;
        // Caminho do .csv
        String path = "./cadastro_estabelecimentos_cnes.csv";
        // Try instanciando os metodos para ler o .csv
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            // Le a primeira linha e não a usa pois é o cabeçalho
            String linha = br.readLine();
            // Le a partir da segunda linha e pega todo o campo de texto
            linha = br.readLine();
            // Enquanto tiver linha
            while(linha != null){
                // Declara um array que quebra a linha separando por ";"
                String[] vect = linha.split(";");
                // Pra cada String dentro de vect[] salva em uma variavel
                // Mas as Strings dentro de vect ja tem ""
                // Ex: ""1"" -> Não da pra converter para INTEGER e tem q fazer o replace
                // O replace troca as aspas de dentro por [vazio]
                Integer CNES = Integer.parseInt(vect[0].replaceAll("\"", ""));
                Integer UFM = Integer.parseInt(vect[1].replaceAll("\"", ""));
                Integer IBGE = Integer.parseInt(vect[2].replaceAll("\"", ""));
                // Não precisa do Replace pois ja é String
                String Nome = (vect[3]);
                String Logradouro = (vect[4]);
                String Bairro = (vect[5]);
                // Instancia uma nova UPA com os valores lidos anteriormente
                UPA lugar = new UPA(CNES, UFM, IBGE, Nome, Logradouro, Bairro);
                // Adiciona o objeto UPA a lista que contem todas as UPAs
                listUPA.add(lugar);
                // Aumenta a quantidade de UPAs
                QttUpa ++;
                // Pula para a proxima linha
                linha = br.readLine();
            }
            System.out.println("UPAS: ");
            System.out.println("<------------------------------>");
            // Para cada objeto p em listaUPA
            for(UPA p : listUPA){
                // Printa o objeto p
                System.out.println(p);
            }
            // Printa a quantidade de UPAs listas e armazenadas <43346>
            System.out.println("Quantidade de UPAS: " + QttUpa);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }    
     }
    
    public static List<UPA> findInUPAS(String numeroDoMunicipio){
        List<UPA> UPAsFiltradas = new ArrayList<UPA>();
        Integer NovoNumeroDoMunicipio = Integer.parseInt(numeroDoMunicipio.substring(0, numeroDoMunicipio.length()-1));
        System.out.println("Numero do IBGE: " + NovoNumeroDoMunicipio);
        for(UPA p : listUPA){
            if(p.getIBGE().equals(NovoNumeroDoMunicipio)){
                System.out.println("p.getIBGE(): " + p.getIBGE() + " || NovoNumeroDoMunicipio: " + NovoNumeroDoMunicipio);
                System.out.println("Boolean True");
                UPAsFiltradas.add(p);
            }else{
                System.out.println("p.getIBGE(): " + p.getIBGE() + " || NovoNumeroDoMunicipio: " + NovoNumeroDoMunicipio);
                System.out.println("Boolean false");
            }
        }
        for(UPA p : UPAsFiltradas){
            System.out.println(p);
        }
        return UPAsFiltradas;
    }
    
    public static void main(String[] args) {
        String NomeMunicipio = "Belo Horizonte";
        getAllMunicipios();
        getAllUPAS();
        List<String> NumeroDoMunicipio = findInMunicipios(NomeMunicipio);
        for(String p : NumeroDoMunicipio){
            findInUPAS(p);
        }
    }
}