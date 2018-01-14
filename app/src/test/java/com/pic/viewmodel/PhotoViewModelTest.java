package com.pic.viewmodel;


import com.pic.config.AppConfig;
import com.pic.service.model.Page;
import com.pic.service.model.Photo;
import com.pic.service.repo.IPageRepo;
import com.pic.view.viewinterface.IPhotoView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class PhotoViewModelTest {

    @Mock
    private IPhotoView mockView;

    @Mock
    private IPageRepo mockRepo;

    PhotoViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockView = mock(IPhotoView.class);
        mockRepo = mock(IPageRepo.class);
        viewModel = new PhotoViewModel(mockView, mockRepo);
    }

    @Test(expected = Exception.class)
    public void test_Load_Pictures_Failed_Service_BackGround_Call() throws Exception {
        when(mockRepo.getPage(AppConfig.API_FEATURE_FRESH_TODAY,
                AppConfig.CONSUMER_KEY)).thenThrow(new Exception("failed execution"));
        viewModel.loadPictures();
        throw new Exception();
    }

    @Test
    public void test_Load_Pictures_Post_Execute_Success() throws Exception {
        when(mockRepo.getPage(AppConfig.API_FEATURE_FRESH_TODAY,
                AppConfig.CONSUMER_KEY)).thenReturn(getMockPage());
        viewModel.loadPictures();
        verify(mockView).onGetPageSuccess(getMockPage());
    }

    @Test
    public void test_Load_Pictures_Post_Execute_Error() throws Exception {
        when(mockRepo.getPage(AppConfig.API_FEATURE_FRESH_TODAY,
                AppConfig.CONSUMER_KEY)).thenReturn(null);
        viewModel.loadPictures();
        verify(mockView).onGetPageError();
    }

    private Page getMockPage() {
        Page page = new Page();
        Photo photo1 = new Photo();
        photo1.setName("photo1");
        photo1.setImageUrl("images/photo1");
        Photo photo2 = new Photo();
        photo2.setName("photo2");
        photo2.setImageUrl("images/photo2");
        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo1);
        photoList.add(photo2);
        page.setPhotos(photoList);
        return page;
    }

}
