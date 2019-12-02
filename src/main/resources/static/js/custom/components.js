let _domParser = new DOMParser();
const createElementFromStr = (domstring) => {
    const html = _domParser.parseFromString(domstring, 'text/html');
    return html.body.firstChild;
};

const createTrFromStr = (domstring) => {
    const html = _domParser.parseFromString("<table><tbody>" + domstring + "</tbody></table>", 'text/html');
    return html.body.firstChild.firstChild.firstChild;
};

const creategalleryImgModel = (name, tag, delay) => {
    let model = {};
    model.name = name;
    model.tag = tag;
    model.delay = delay;
    return model;
};

function creategalleryImg(img, tag, delay) {
    let singleGallayString = '<div class="col-12 col-sm-6 col-lg-3 single_gallery_item '
        + tag + ' mb-30 wow fadeInUp" data-wow-delay="'
        + delay + 'ms"><div class="single-portfolio-content"><img src="'
        + img + '" alt=""><div class="hover-content"><a href="'
        + img + '" class="portfolio-img">+</a></div></div></div>'
    return createElementFromStr(singleGallayString);
    // document.getElementById("alime-portfolio").append(singleGallayItem);
}

function createBreadcrumbSection(article) {

    let section = '<section class="breadcrumb-area blog bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/18.jpg);">\n' +
        '        <div class="container h-100">\n' +
        '            <div class="row h-100 align-items-center">\n' +
        '                <div class="col-12">\n' +
        '                    <div class="breadcrumb-content text-center">\n' +
        '                        <a href="#" class="btn post-catagory">' + article['category'] + '</a>\n' +
        '                        <h2 class="page-title">' + article['subject'] + '</h2>\n' +
        '                        <div class="post-meta">\n' +
        '                            <a href="#" class="post-author"> By&nbsp;' + article['author']['nickname'] + '</a>\n' +
        '                            <a href="#" class="post-date">' + article['created_at'] + '</a>\n' +
        '                            <a href="#" class="post-comments" id="postComments">'+ article['comments'].length+ ' Comments</a>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </section>';
    let sectionElement = createElementFromStr(section);
    return sectionElement;
}

function createFollowAreaImg(imgName) {
    let followAreaImg = '<div class="single-instagram-item">\n' + '<img src="img/bg-img/'
        + imgName + '.jpg" alt="">\n' + '<div class="instagram-hover-content text-center d-flex align-items-center justify-content-center">\n' + '<a href="#">\n' + '<i class="ti-instagram" aria-hidden="true"></i>\n'
        + '<span>Best of the world,Tasha</span>\n' + '</a>\n' + '</div>\n' + '</div>'
    return createElementFromStr(followAreaImg);
}

function appendFollowAreaImgs(imgs){
    imgs = ['boyoung_test', "3","18","4","5","6","7"];
    for (let i=0; i  < imgs.length; i++){
        document.getElementById("instagram-feed-area-id").append(createFollowAreaImg(imgs[i]));
    }
}

function appendGalleryImgs(imgs) {
    imgs = [creategalleryImgModel("devday2018.png", "human", 500), creategalleryImgModel("ana_2.png", "country", 200)
        , creategalleryImgModel("ucpc.png", "human", 700), creategalleryImgModel("sw_festival.png", "human", 700)
        , creategalleryImgModel("ant2.jpg", "human", 700), creategalleryImgModel("poster.png", "human", 700)];

    for (let i = 0; i < imgs.length; i++) {
        try{
            let singleGallayItem = creategalleryImg("/img/bg-img/" + imgs[i].name , imgs[i].tag,imgs[i].delay);
            document.getElementById("alime-portfolio").append(singleGallayItem);
        }catch (e) {
            console.dir(e)
            console.dir("gallery img error: index: " + i)

        }

    }
}
