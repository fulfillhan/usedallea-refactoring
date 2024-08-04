(function () {
    'use strict';

    const noExistsImage = () => {
        const images = document.querySelectorAll('.slide-wrap img');
        return images.length <= 0;
    };

    const initImage = () => {
        const images = document.querySelectorAll('.slide-wrap img');
        images.forEach((img, index) => {
            img.style.left = `${(index * 100)}%`;
        });
        return images.length;
    };

    const createSlider = (imageCount) => {
        return {
            firstIndex: 0,
            lastIndex: imageCount - 1,
            currentSlideIndex: 0,
            slider: document.querySelector('.slide-wrap'),
            pageButtons: document.querySelectorAll('.slide-pagination-wrap > div'),

            isFirstSlide() {
                return this.currentSlideIndex === this.firstIndex;
            },
            isLastSlide() {
                return this.currentSlideIndex === this.lastIndex;
            },
            showPrev() {
                if (this.isFirstSlide()) {
                    this.show(this.lastIndex);
                } else {
                    this.show(this.currentSlideIndex - 1);
                }
            },
            showNext() {
                if (this.isLastSlide()) {
                    this.show(this.firstIndex);
                } else {
                    this.show(this.currentSlideIndex + 1);
                }
            },
            show(targetIndex) {
                this.currentSlideIndex = targetIndex;

                this.slider.style.transform = `translateX(-${targetIndex * 300 / (imageCount - 1)}%)`;

                this.pageButtons.forEach((button, index) => {
                    if (index === this.currentSlideIndex) {
                        button.classList.add('active');
                    } else {
                        button.classList.remove('active');
                    }
                });
            },

        };
    };

    const bindSlideEvent = (slider) => {
        const pageButtons = document.querySelectorAll('.slide-pagination-wrap > div');
        pageButtons.forEach((pageButton, index) => {
            pageButton.addEventListener('click', () => slider.show(index));
        });

        const prevButton = document.querySelector('.slide-button-wrap > .pre');
        prevButton.addEventListener('click', () => slider.showPrev());

        const nextButton = document.querySelector('.slide-button-wrap > .next');
        nextButton.addEventListener('click', () => slider.showNext());
    };

    const startAutoSlide = (slider, imageCount) => {
        window.setInterval(() => slider.showNext(), 4000);
    }

    if (noExistsImage()) {
        return;
    }

    const imageCount = initImage();
    const slider = createSlider(imageCount);
    bindSlideEvent(slider);
    startAutoSlide(slider, imageCount);
}());