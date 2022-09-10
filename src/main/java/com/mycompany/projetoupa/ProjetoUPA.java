/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.projetoupa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.in;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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
    
    public static void deleteAllResources(){
        File file = new File( "./Unidades_Basicas_Saude-UBS.zip" );
        file.delete();
        File file2 = new File( "./DTB_2021.zip"); 
        file2.delete();
        File file3 = new File( "./RELATORIO_DTB_BRASIL_DISTRITO.ods"); 
        file3.delete();
        File file4 = new File( "./RELATORIO_DTB_BRASIL_DISTRITO.xls"); 
        file4.delete();
        File file5 = new File( "./RELATORIO_DTB_BRASIL_MUNICIPIO.ods"); 
        file5.delete();
        File file6 = new File( "./RELATORIO_DTB_BRASIL_MUNICIPIO.xls"); 
        file6.delete();
        File file7 = new File( "./RELATORIO_DTB_BRASIL_SUBDISTRITO.xls"); 
        file7.delete();
        File file8 = new File( "./RELATORIO_DTB_BRASIL_SUBDISTRITO.xls"); 
        file8.delete();
        File file9 = new File( "./Unidades_Basicas_Saude-UBS.csv");
        file9.delete();
    }
    
    public static void downloadResources(){
        String httpFile = "https://geoftp.ibge.gov.br/organizacao_do_territorio/estrutura_territorial/divisao_territorial/2021/DTB_2021.zip";
        String localFile = "./DTB_2021 .zip";
        
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

        //httpFile = "https://sage.saude.gov.br/dados/repositorio/cadastro_estabelecimentos_cnes.zip";
        httpFile = "https://s3.sa-east-1.amazonaws.com/ckan.saude.gov.br/CNES/Unidades_Basicas_Saude-UBS.zip";
        localFile = "./Unidades_Basicas_Saude-UBS.zip";
        
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
    
    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static void unzipAllFiles(){
        String zipFilePath = "./DTB_2021 .zip";
        String destDir = "./";
        unzip(zipFilePath, destDir);
        
        zipFilePath = "./Unidades_Basicas_Saude-UBS.zip";
        destDir = "./";
        unzip(zipFilePath, destDir);
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
        String path = "./Unidades_Basicas_Saude-UBS.csv";
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
    
    public static List<String> findMunicipiosByUF(String UF){
        // Array List que serão guardados os nomes do municipio
        List<String> getMunicipios = new ArrayList<String>();
        // Inteiro que ira guardar a quantidade de municipios do estado selecionado
        Integer qttMunicipios = 0;
        // Para cada Municipio p em listMunicipios
        for(Municipio p : listMunicipios){
            // Se o Municipio p na variavel Nome_UF for igual ao atributo recebido
            if(p.getNome_UF().equals(UF)){
                // Adiciona o Nome_Municipio desse Objeto
                getMunicipios.add(p.getNome_Munic());
                qttMunicipios++;
            }
        }
        // Printa todos os Municipios dentro de getMunicipios
        System.out.println("Municipios do Estado de: " + UF);
        for(String p : getMunicipios){
            System.out.println("Municipio: " + p);
        }
        System.out.println("Quantidade de Cidades Lidas: " + qttMunicipios);
        
        return getMunicipios;
    }

    public static void main(String[] args) {
        deleteAllResources();
        downloadResources();
        unzipAllFiles();
        getAllMunicipios();
        getAllUPAS();
        Scanner ler = new Scanner(in);
        Scanner s = new Scanner(System.in);

        int op;

        do {
            System.out.println("-------------------------------------------");
            System.out.println("|                   MENU                   |");
            System.out.println("-------------------------------------------");
            System.out.println("|                                          |");
            System.out.println("| 0 - Sair                                 |");
            System.out.println("| 1 - Busca por estado                     |");
            System.out.println("| 2 - Busca por cidade                     |");
            System.out.println("| 3 - Baixar tudo novamente                |");
            System.out.println("--------------------------------------------");
            System.out.println("Digite sua opção:");

            op = Integer.parseInt(s.nextLine());

            switch (op) {
                case 0: {
                    System.out.println("Programa Finalizado!");
                    break;
                }
                case 1: {
                    System.out.println("Digite o Nome do estado que deseja filtar: ");
                    String UF = ler.nextLine();
                    System.out.println(UF);
                    findMunicipiosByUF(UF);
                    break;
                }
                case 2: {
                    System.out.println("Digite o Nome da Cidade que deseja pesquisar: ");
                    String NomeMunicipio = ler.nextLine();
                    List<String> NumeroDoMunicipio = findInMunicipios(NomeMunicipio);
                    for(String p : NumeroDoMunicipio){
                        findInUPAS(p);
                    }
                    break;
                }
                case 3: {
                    deleteAllResources();
                    downloadResources();
                    unzipAllFiles();
                    getAllMunicipios();
                    getAllUPAS();
                    break;
                }
            }

        } while (op != 0);
    }
}