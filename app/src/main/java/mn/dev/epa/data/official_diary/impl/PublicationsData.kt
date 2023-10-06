package mn.dev.epa.data.official_diary.impl

import mn.dev.epa.model.Publication
import mn.dev.epa.model.PublicationsFeed
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("pt-Br"))

val publication1 = Publication(
    id = UUID.fromString("03/10/2023|3").toString(),
    title = "DECRETO MUNICIPAL N˚ 1049/2023",
    date = formatter.parse("03/10/2023")!!,
    agency = "PREFEITURA",
    city = "PEDRO ALEXANDRE",
    resume = "EXONERA A SRA. SILVANEIDE TAVARES GOES, DO CARGO DE DIRETORA ESCOLAR DO COLÉGIO GUILHERMINA DE ALMEIDA.",
    type = "Atos Oficiais.",
    url = "https://pmpedroalexandre.transparenciaoficialba.com/arquivos/publicacoes/2023/PM_PEDRO_ALEXANDRE_03_10_23_01.pdf"
)

val publication2 = Publication(
    id = UUID.fromString("03/10/2023|2").toString(),
    title = "RATIFICAÇÃO/DISPENSA DE LICITAÇÃO Nº035/2023",
    date = formatter.parse("03/10/2023")!!,
    agency = "PREFEITURA",
    city = "PEDRO ALEXANDRE",
    resume = "CONTRATAÇÃO DE EMPRESA ESPECIALIZADA EM LOCAÇÃO DE CAVALO MECÂNICO COM PRANCHA 03(TRÊS) EIXOS PARA REALIZAR O TRANSPORTE DE MÁQUINA PESADA DE JUAZEIRO - BA (COODEVASF), PARA A CIDADE.",
    type = "Licitações e Contratos.",
    url = "https://pmpedroalexandre.transparenciaoficialba.com/arquivos/publicacoes/2023/PM_PEDRO_ALEXANDRE_03_10_23_02.pdf"
)

val publication3 = Publication(
    id = UUID.fromString("03/10/2023|1").toString(),
    title = "EXTRATO DO CONTRATO Nº 121/DISPENSA DE LICITAÇÃO Nº035/2023",
    date = formatter.parse("03/10/2023")!!,
    agency = "PREFEITURA",
    city = "PEDRO ALEXANDRE",
    resume = "Contratada: RODRIGUES AUTO CENTER LTDA - ME.",
    type = "Licitações e Contratos.",
    url = "https://pmpedroalexandre.transparenciaoficialba.com/arquivos/publicacoes/2023/PM_PEDRO_ALEXANDRE_03_10_23_02.pdf"
)

val publications: PublicationsFeed = PublicationsFeed(
    recentPublications = emptyList(),
    publications = listOf(publication1, publication2, publication3)
)