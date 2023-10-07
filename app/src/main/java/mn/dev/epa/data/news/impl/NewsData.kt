package mn.dev.epa.data.news.impl

import mn.dev.epa.model.News
import mn.dev.epa.model.NewsFeed

const val AUTHOR = "ASCOM"

val news1 = News(
    id = "712/",
    title = "Prefeitura de Pedro Alexandre e Fundação Cultural do Estado anunciam o VI Seminário de Criação em Dança",
    author = AUTHOR,
    urlImage = "https://pedroalexandre.ba.gov.br/admin/noticias/6515b0371c88f.png",
    url = "https://pedroalexandre.ba.gov.br/noticia/712/prefeitura-de-pedro-alexandre-e-funda-o-cultural-do-estado-anunciam-o-vi-semin-rio-de-cria-o-em-dan-a",
    date = "28/09/2023 às 13:52:22"
)

val news2 = News(
    id = "711/",
    title = "Formações Educacionais em Pedro Alexandre Fortalecem a Atuação Docente na Educação Infantil e EJA",
    author = AUTHOR,
    urlImage = "https://pedroalexandre.ba.gov.br/admin/noticias/6511db05d67e9.jpeg",
    url = "https://pedroalexandre.ba.gov.br/noticia/711/forma-es-educacionais-em-pedro-alexandre-fortalecem-a-atua-o-docente-na-educa-o-infantil-e-eja",
    date = "25/09/2023 às 16:07:42"
)
val news3 = News(
    id = "710/",
    title = "Inscrições do Programa Garantia Safra são realizadas em Pedro Alexandre",
    author = AUTHOR,
    urlImage = "https://pedroalexandre.ba.gov.br/admin/noticias/650ed05514761.jpg",
    url = "https://pedroalexandre.ba.gov.br/noticia/710/inscri-es-do-programa-garantia-safra-s-o-realizadas-em-pedro-alexandre",
    date = " 23/09/2023 às 08:41:53"
)
val news4 = News(
    id = "709/",
    title = "Regulamentação do Pagamento do Piso da Enfermagem Aprovada na Câmara de Vereadores de Pedro Alexandre",
    author = AUTHOR,
    urlImage = "https://pedroalexandre.ba.gov.br/admin/noticias/650ca4b47e4eb.jpeg",
    url = "https://pedroalexandre.ba.gov.br/noticia/709/regulamenta-o-do-pagamento-do-piso-da-enfermagem-aprovada-na-c-mara-de-vereadores-de-pedro-alexandre",
    date = "21/09/2023 às 17:13:49"
)
val news5 = News(
    id = "708/",
    title = "Encerramento da Pós-Especialização em Melhoria do Cuidado em APS fortalece atuação da Saúde Municipal",
    author = AUTHOR,
    urlImage = "https://pedroalexandre.ba.gov.br/admin/noticias/6505de2fe135f.jpeg",
    url = "https://pedroalexandre.ba.gov.br/noticia/708/encerramento-da-p-s-especializa-o-em-melhoria-do-cuidado-em-aps-fortalece-atua-o-da-sa-de-municipal",
    date = "16/09/2023 às 13:55:27"
)

val news: NewsFeed = NewsFeed(

    highlightedNews = listOf(news1, news2, news3),
    news = listOf(news4, news5)
)